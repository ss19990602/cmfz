package com.baizhi.serviceimpl;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.service.ArticleSevice;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
class ArticleSeviceImpl implements ArticleSevice {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> slesct(Integer start, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer s = (start - 1) * rows;
        List<Article> articles = articleMapper.selectAll(s, rows);
        Integer count = articleMapper.count();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;//页数
        map.put("rows", articles);
        map.put("records", count);
        map.put("total", total);
        map.put("page", start);
        return map;
    }

    @Override
    public void Insert(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        article.setCreatDate(new Date());
        articleMapper.insert(article);
    }

    @Override
    public void Update(Article article, String id) {
        article.setId(id);
        articleMapper.update(article);
    }

    @Override
    public List<Article> queryByes(String val) {
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        field.preTags("<span style='color:red'>");
        field.postTags("</span>");
        NativeSearchQuery build = new NativeSearchQueryBuilder().withIndices("cmfz")
                .withTypes("article")
                .withQuery(QueryBuilders.queryStringQuery(val).analyzer("ik_max_word").field("title").field("content"))
                .withHighlightFields(field)
                .build();
        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(build, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                ArrayList<Article> list = new ArrayList<>();
                SearchHits hits = searchResponse.getHits();
                SearchHit[] hits1 = hits.getHits();
                for (SearchHit documentFields : hits1) {
                    Article article = new Article();
                    Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
                    article.setId(sourceAsMap.get("id").toString());
                    article.setAuthor(sourceAsMap.get("author").toString());
                    article.setTitle(sourceAsMap.get("title").toString());
                    article.setContent(sourceAsMap.get("content").toString());
                    article.setStatus(sourceAsMap.get("status").toString());
                    Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
                    if(highlightFields.get("title")!=null){
                        String title = highlightFields.get("title").getFragments()[0].toString();
                        article.setTitle(title);
                    }
                    if(highlightFields.get("content") != null){
                        String content = highlightFields.get("content").getFragments()[0].toString();
                        article.setContent(content);
                    }
                    list.add(article);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }
        });
        return articles.getContent();
    }
}

package com.baizhi.serviceimpl;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.service.ArticleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
class ArticleSeviceImpl implements ArticleSevice {
    @Autowired
    ArticleMapper articleMapper;

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
}

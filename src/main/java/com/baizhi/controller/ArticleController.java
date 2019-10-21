package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ArticleController")
public class ArticleController {
    @Autowired
    ArticleSevice articleSevice;

    @RequestMapping("select")
    @ResponseBody
    public Map<String, Object> select(Integer page, Integer rows) {
        Map<String, Object> slesct = articleSevice.slesct(page, rows);
        return slesct;
    }
    @RequestMapping("add")
    @ResponseBody
    public void insert(Article article) {
        articleSevice.Insert(article);
    }

    @RequestMapping("update")
    @ResponseBody
    public void Update(Article article, String id) {
        articleSevice.Update(article, id);
    }
    @RequestMapping("ESselect")
    @ResponseBody
    public List<Article> ESselect(String val){
        List<Article> articles = articleSevice.queryByes(val);
        return articles;
    }
}

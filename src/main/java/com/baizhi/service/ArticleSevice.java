package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleSevice {
    public Map<String, Object> slesct(Integer start, Integer rows);

    public void Insert(Article article);

    public void Update(Article article, String id);
}

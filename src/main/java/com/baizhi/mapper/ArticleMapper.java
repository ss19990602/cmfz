package com.baizhi.mapper;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    public List<Article> selectAll(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer count();

    public void insert(Article article);

    public void update(Article article);

    public List<Article> selectal();
}

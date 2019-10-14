package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    List<Chapter> selectAll(@Param("start") Integer start, @Param("rows") Integer rows, @Param("id") String id);

    Integer count(String id);

    void insert(Chapter chapter);

    void Update(Chapter chapter);

    void delete(String id);

    void UpdateName(@Param("id") String id, @Param("title") String title);

    void Updatecount(@Param("id") String id, @Param("count") Integer count);
}

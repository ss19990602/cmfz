package com.baizhi.mapper;

import com.baizhi.entity.picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureMapper {
    public List<picture> selectPicture(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer count();

    public void insertPicture(picture picture);//添加数据

    public void DeletePicture(String id);//删除数据

    public void Updatesrc(@Param("id") String id, @Param("src") String src);

    public void Update(@Param("id") String id, @Param("state") String state);

    public List<picture> selectAll();
}

package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    List<Album> selectAll(@Param("start") Integer start, @Param("rows") Integer rows);

    Integer count();

    void insertAlbum(Album album);

    void UpdateSrc(@Param("id") String id, @Param("src") String src);

    void delete(String id);

    void UpdateState(@Param("id") String id, @Param("state") String state);

}

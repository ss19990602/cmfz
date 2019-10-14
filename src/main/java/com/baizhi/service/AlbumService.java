package com.baizhi.service;

import com.baizhi.entity.Album;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AlbumService {
    public Map<String, Object> selectPage(Integer start, Integer rows);

    public String edit(String oper, Album album, String id);

    public void updateSrc(MultipartFile multipartFile, String id, HttpSession session);
}

package com.baizhi.service;

import com.baizhi.entity.picture;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface PictureService {
    public Map<String, Object> selectPage(Integer start, Integer rows);

    public String edit(String oper, picture picture, String id);

    public void Update(MultipartFile multipartFile, String id, HttpSession session);

    public void derive(HttpServletRequest request, HttpServletResponse response);
}

package com.baizhi.service;


import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ChapterService {
    Map<String, Object> select(Integer start, Integer rows, String id);

    String edit(String oper, Chapter chapter, String id, String fid);

    void Update(MultipartFile multipartFile, String id, HttpSession session);
}

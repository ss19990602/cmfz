package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("ChapterController")
public class ChapterController {
    @Autowired
    ChapterService service;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows, String id) {
        return service.select(page, rows, id);
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(String oper, Chapter chapter, String id, String fid) {
        String edit = service.edit(oper, chapter, id, fid);
        return edit;
    }

    @ResponseBody
    @RequestMapping("update")
    public void update(MultipartFile name, String id, HttpSession session) {
        service.Update(name, id, session);
    }

    @RequestMapping("/download")
    @ResponseBody
    public void download(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取目标文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/mp3");
        //读入
        FileInputStream fis = new FileInputStream(new File(realPath, filename));

        //写出
        ServletOutputStream os = response.getOutputStream();
        //设置响应头
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));

        IOUtils.copy(fis, os);
        //关流
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);
    }
}

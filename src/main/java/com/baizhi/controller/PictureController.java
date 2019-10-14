package com.baizhi.controller;

import com.baizhi.entity.picture;
import com.baizhi.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("PictureController")
public class PictureController {
    @Autowired
    PictureService service;

    @RequestMapping("select")
    @ResponseBody
    public Map<String, Object> select(Integer page, Integer rows) {
        Map<String, Object> map = service.selectPage(page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(String oper, picture pictuer, String id) {
        String edit = service.edit(oper, pictuer, id);
        return edit;
    }

    @ResponseBody
    @RequestMapping("update")
    public void update(MultipartFile src, String id, HttpSession session) {
        service.Update(src, id, session);
    }

    @ResponseBody
    @RequestMapping("dc")
    public void dc(HttpServletRequest request, HttpServletResponse response) {
        service.derive(request, response);
    }
}

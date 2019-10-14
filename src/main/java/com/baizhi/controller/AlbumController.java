package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("AlbumController")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectall(Integer page, Integer rows) {
        Map<String, Object> stringObjectMap = albumService.selectPage(page, rows);
        return stringObjectMap;
    }

    @ResponseBody
    @RequestMapping("edit")
    public String edit(String oper, Album album, String id) {
        String edit = albumService.edit(oper, album, id);
        return edit;
    }

    @ResponseBody
    @RequestMapping("update")
    public void update(MultipartFile src, String id, HttpSession session) {
        albumService.updateSrc(src, id, session);
    }
}

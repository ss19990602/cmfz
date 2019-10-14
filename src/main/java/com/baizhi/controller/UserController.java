package com.baizhi.controller;

import com.baizhi.entity.testmape;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("UserController")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping("select")
    @ResponseBody
    public List<testmape> select() {
        return service.select();
    }

    @ResponseBody
    @RequestMapping("query")
    public List<Integer> query() {
        List<Integer> count = service.count();
        return count;
    }
}

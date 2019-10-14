package com.baizhi.controller;


import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.yzmAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("cmfz")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    yzmAction action;

    @RequestMapping("login")
    @ResponseBody
    public String Login(Admin admin, HttpSession session, String code) {
        String login = adminService.Login(admin, session, code);
        return login;
    }

    @RequestMapping("code")//验证码
    @ResponseBody
    public void code(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        action.init();
        action.doPost(request, response);
        action.destroy();
    }

    @RequestMapping("Logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}

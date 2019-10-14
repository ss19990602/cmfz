package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public String Login(Admin admin, HttpSession session, String code);
}

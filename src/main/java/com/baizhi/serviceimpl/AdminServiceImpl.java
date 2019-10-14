package com.baizhi.serviceimpl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String Login(Admin admin, HttpSession session, String code) {
        Admin login = adminMapper.Login(admin.getUsername());
        String qwerdf = (String) session.getAttribute("qwerdf");
        if (!code.equals(qwerdf)) {
            return "验证码输入错误";
        } else if (login == null) {
            return "用户不存在";
        } else if (!login.getPassword().equals(admin.getPassword())) {
            return "密码输入错误";
        } else {
            session.setAttribute("login", login);
            return null;
        }
    }
}

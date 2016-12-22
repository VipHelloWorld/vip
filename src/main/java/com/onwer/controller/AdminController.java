package com.onwer.controller;

import com.onwer.dao.AdminDao;
import com.onwer.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dell on 2016/12/22.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminDao adminDao;

    @RequestMapping("register")
    public void insertUser(HttpServletResponse response, HttpServletRequest request, String name, String email, String phone, String openId, String password) {
        adminDao.insertUser(name, email, phone, openId, password);
        new ResponseUtil().doResponse(request, response, "ok");
    }

    @RequestMapping("login")
    public void insertUser(HttpServletResponse response, HttpServletRequest request,String password, String phone) {
        adminDao.findByLogin(phone,password);
        new ResponseUtil().doResponse(request, response, "ok");
    }

}

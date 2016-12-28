package com.onwer.controller;

import com.onwer.service.UserService;
import com.onwer.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by dell on 2016/12/23.
 */
@Controller
@RequestMapping("msg")
public class InfoMsgController {
    @Autowired
    private UserService userService;

    @RequestMapping("count")
    public void queryCount(HttpServletRequest request, HttpServletResponse response) {
        Map count = userService.queryCount();
        new ResponseUtil().doResponse(request, response, count);
    }
}

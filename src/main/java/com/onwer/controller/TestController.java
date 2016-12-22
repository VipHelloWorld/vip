package com.onwer.controller;

import com.onwer.dao.AdminDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.util.parsing.combinator.testing.Str;

/**
 * Created by dell on 2016/12/14.
 */

@Controller
@RequestMapping("test")
public class TestController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminDao adminDao;

    @RequestMapping(value = "returnString", produces = {"text/plain;charset=UTF-8"})
    //produces用于解决返回中文乱码问题，application/json;为json解决中文乱码
    @ResponseBody       //用于返回字符串,不写即返回视图
    public String returnString() {
        return "hello";
    }


    @RequestMapping(value = "test")
    public String demo() {
        logger.info("----info---");
        logger.debug("----debug---");
        logger.error("----error---");
        return "view/test";
    }

}

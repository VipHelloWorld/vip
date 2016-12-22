package com.onwer.service;

import com.onwer.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2016/12/22.
 */
@Service
public class UserService {

    @Autowired
    private AdminDao adminDao;

    public void insertUser(String name, String phone, String email, String openId, String passoword) {
        adminDao.insertUser(name,email,phone,openId,passoword);
    }
}

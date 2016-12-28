package com.onwer.service;

import com.onwer.dao.AdminDao;
import com.onwer.dao.InfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/12/22.
 */
@Service
public class UserService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private InfoDao infoDao;

    public void insertUser(String name, String phone, String email, String openId, String passoword) {
        adminDao.insertUser(name,email,phone,openId,passoword);
    }


    public Map queryCount(){
        HashMap hashMap = new HashMap();
        Integer count = infoDao.findCount();
        Integer countByDay = infoDao.findCountByDay();
        List<Map> mapByGroup = infoDao.findCountByGroup();
        hashMap.put("count",count);
        hashMap.put("countByDay",countByDay);
        hashMap.put("mapByGroup",mapByGroup);
        return hashMap;
    }
}

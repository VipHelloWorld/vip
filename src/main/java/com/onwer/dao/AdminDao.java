package com.onwer.dao;

import com.onwer.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by dell on 2016/12/22.
 */
@Repository
public interface AdminDao {
    void insertUser(@Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("openId")String openId,@Param("password") String password);
    Admin findByLogin(@Param("phone") String phone,@Param("password") String password);
}

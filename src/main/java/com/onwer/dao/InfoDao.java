package com.onwer.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/12/23.
 */

@Repository
public interface InfoDao {
    Integer findCount();
    Integer findCountByDay();
    List<Map> findCountByGroup();
}


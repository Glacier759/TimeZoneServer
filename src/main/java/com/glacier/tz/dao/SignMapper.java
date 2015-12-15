package com.glacier.tz.dao;

import com.glacier.tz.model.Sign;

import java.util.HashMap;
import java.util.List;

public interface SignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);

    List<Sign> selectSignRecords(HashMap<String,String> params);
}
package com.glacier.tz.dao;

import com.glacier.tz.model.Student;

import java.util.HashMap;
import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKeyWithBLOBs(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAllStudents();

    Student selectByStudentID(String stuId);

    int updateIntroductionByAccessToken(HashMap params);

    Student selectByAccessToken(String accessToken);

    String selectAccessTokenByStuID(String stuId);

    String selectStuIDByAccessToken(String accessToken);
}
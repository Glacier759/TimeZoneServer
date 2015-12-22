package com.glacier.tz.service;

import com.glacier.tz.model.Student;

import java.util.List;

/**
 * Created by Glacierlx on 2015/12/15.
 */
public interface AccountService {

    Student login(String tid, String password);

    int updateIntroduction(String accessToken, String introduction);

    Student getStudentByAccessToken(String accessToken);

    List<Student> selectAllStudents();

    String selectAccessTokenByStuID(String stuID);

    String selectStuIDByAccessToken(String accessToken);

    boolean isAccessTokenBelongStuID(String accessToken, String stuID);

}

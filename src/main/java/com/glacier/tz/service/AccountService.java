package com.glacier.tz.service;

import com.glacier.tz.model.Student;

/**
 * Created by Glacierlx on 2015/12/15.
 */
public interface AccountService {

    public Student login(String tid, String password);

    public int updateIntroduction(String accessToken, String introduction);

    public Student getStudentByAccessToken(String accessToken);
}

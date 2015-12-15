package com.glacier.tz.service;

import com.glacier.tz.model.Student;

import java.util.List;

/**
 * Created by Glacierlx on 2015/12/14.
 */
public interface Login {

    public Student login(String tid, String password);

    public List<Student> test();

}

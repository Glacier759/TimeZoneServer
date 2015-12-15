package com.glacier.tz.controller;

import com.glacier.tz.model.Student;
import com.glacier.tz.service.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by glacier on 15-12-2.
 */

@Controller
@RequestMapping("/sign")
public class SignController {

    @Resource
    private Login login;

    @ResponseBody
    @RequestMapping("/ha")
    public Student hahaha() {
        return login.login("04121110", "glacierlx1994");
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Student> test() {
        return login.test();
    }

}

package com.glacier.tz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.model.Student;
import com.glacier.tz.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by glacier on 15-12-2.
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public JSONObject login(@RequestParam("username")String username, @RequestParam("password")String password) {

        Student student = accountService.login(username, password);
        JSONObject result = new JSONObject();

        if ( student != null ) {
            result.put("status", 200);
            result.put("info", JSON.parseObject(JSON.toJSONString(student)));
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "登录失败，账号密码不匹配");
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/introduction", method = RequestMethod.GET)
    public JSONObject information(@RequestParam("accessToken")String accessToken, @RequestParam("content")String introduction) {

        JSONObject result = new JSONObject();
        if ( accountService.updateIntroduction(accessToken, introduction) != 0){
            result.put("status", 200);
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "更新失败，AccessToken无效");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public JSONObject information(@RequestParam("accessToken")String accessToken) {

        JSONObject result = new JSONObject();
        Student student = accountService.getStudentByAccessToken(accessToken);
        if ( student != null ){
            result.put("status", 200);
            result.put("info", JSON.parseObject(JSON.toJSONString(student)));
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken无效");
        }
        return result;
    }

}

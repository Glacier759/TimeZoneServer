package com.glacier.tz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.model.Student;
import com.glacier.tz.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by glacier on 15-12-2.
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    private static Logger logger = Logger.getLogger(AccountController.class);
    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject login(@RequestParam("username")String username, @RequestParam("password")String password) {
        logger.info("[controller] login - username: " + username +"\tpassword: " + password);
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
    @RequestMapping(value = "/{stuID}", method = RequestMethod.PUT)
    public JSONObject introduction(@RequestParam("accessToken")String accessToken, @PathVariable("stuID")String stuID, @RequestParam("content")String introduction) {
        logger.info("[controller] update introduction - stuID: " + stuID + "\taccessToken: " + accessToken + "\tcontent: " + introduction);
        JSONObject result = new JSONObject();
        if ( !accountService.isAccessTokenBelongStuID(accessToken, stuID) ) {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken与StuID不匹配");
        }
        else if ( accountService.updateIntroduction(accessToken, introduction) != 0){
            result.put("status", 200);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/{stuID}", method = RequestMethod.GET)
    public JSONObject information(@PathVariable("stuID")String stuID) {
        logger.info("[controller] get information - stuID: " + stuID);
        JSONObject result = new JSONObject();
        Student student = null;
        String accessToken = null;

        if ( (accessToken = accountService.selectAccessTokenByStuID(stuID)) == null ) {
            result.put("status", 500);
            result.put("errorMessage", "无该用户");
        }
        else if ( (student = accountService.getStudentByAccessToken(accessToken)) != null ){
            result.put("status", 200);
            JSONObject infobj = JSON.parseObject(JSON.toJSONString(student));
            infobj.remove("accessToken");
            result.put("info", infobj);
        }
        return result;
    }

}

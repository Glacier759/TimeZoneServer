package com.glacier.tz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.model.Student;
import com.glacier.tz.service.SignService;
import com.glacier.tz.utils.JsonUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by glacier on 15-12-2.
 */

@Controller
@RequestMapping("/sign")
public class SignController {

    @Resource
    private SignService signService;
    @Resource
    private JsonUtils jsonUtils;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@Param("username")String username, @Param("password")String password) {

        Student student = signService.login(username, password);

        if ( student != null ) {
            jsonUtils.setStatus(200);
            jsonUtils.setInfoByString(JSON.toJSONString(student));
        }

        return jsonUtils.toString();
    }

}

package com.glacier.tz.controller;

import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.service.AccountService;
import com.glacier.tz.service.SignService;
import com.glacier.tz.utils.TimeUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Glacierlx on 2015/12/15.
 */
@RequestMapping("/sign")
@Controller
public class SignController {

    @Resource
    private SignService signService;
    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public JSONObject signin(@RequestParam("accessToken") String accessToken) {
        JSONObject result = new JSONObject();

        if (accountService.getStudentByAccessToken(accessToken) != null
                && signService.signOperation(accessToken, 1) != 0) {
            result.put("status", 200);
        } else {
            result.put("status", 500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/out", method = RequestMethod.GET)
    public JSONObject signout(@RequestParam("accessToken") String accessToken) {
        JSONObject result = new JSONObject();
        if (accountService.getStudentByAccessToken(accessToken) != null
                && signService.signOperation(accessToken, 0) != 0) {
            result.put("status", 200);
        } else {
            result.put("status", 500);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/history_query", method = RequestMethod.GET)
    public JSONObject history_query(@RequestParam("query") String query) {
        JSONObject result = new JSONObject();
        String beginDate = null, endDate = null;
        switch (query) {
            case "today":
            case "day":
                beginDate = TimeUtils.daySkip(0);
                endDate = TimeUtils.daySkip(1);
                break;
            case "week":
                beginDate = TimeUtils.weekSkip(0);
                endDate = TimeUtils.weekSkip(1);
                break;
            case "month":
                beginDate = TimeUtils.monthSkip(0);
                endDate = TimeUtils.monthSkip(1);
                break;
            case "year":
                beginDate = TimeUtils.yearSkip(0);
                endDate = TimeUtils.yearSkip(1);
                break;
            case "yesterday":
                beginDate = TimeUtils.daySkip(-1);
                endDate = TimeUtils.daySkip(0);
                break;
            case "last_week":
                beginDate = TimeUtils.weekSkip(-1);
                endDate = TimeUtils.weekSkip(0);
                break;
            case "last_month":
                beginDate = TimeUtils.monthSkip(-1);
                endDate = TimeUtils.monthSkip(0);
                break;
            case "last_year":
                beginDate = TimeUtils.yearSkip(-1);
                endDate = TimeUtils.yearSkip(0);
                break;
        }
        result.put("status", 200);
        result.put("records", signService.getRecordsByDate(beginDate, endDate));

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/history_time", method = RequestMethod.GET)
    public JSONObject histroy_time(@RequestParam("begin") String beginDate, @RequestParam("end") String endDate) {
        JSONObject result = new JSONObject();
        if (beginDate != null && endDate != null) {
            if (TimeUtils.checkDateFormat(beginDate) && TimeUtils.checkDateFormat(endDate)) {
                result.put("status", 200);
                result.put("records", signService.getRecordsByDate(beginDate, endDate));
            } else {
                result.put("status", 500);
                result.put("errorMessage", "輸入日期格式有誤");
            }
        } else if (beginDate == null && endDate == null) {
            result.put("status", 500);
            result.put("errorMessage", "無參數");
        } else {
            if (beginDate != null && TimeUtils.checkDateFormat(beginDate)) {
                endDate = TimeUtils.daySkip(1);
            }
            if (endDate != null && TimeUtils.checkDateFormat(endDate)) {
                beginDate = TimeUtils.daySkip(0);
            }
            result.put("status", 200);
            result.put("records", signService.getRecordsByDate(beginDate, endDate));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/history_query_person", method = RequestMethod.GET)
    public JSONObject history_query_person(@RequestParam("stuID") String stuID, @RequestParam("query") String query) {
        JSONObject result = new JSONObject();
        if (accountService.selectAccessTokenByStuID(stuID) == null) {
            result.put("status", 500);
            result.put("errorMessage", "该用户在系统中不存在");
            return result;
        }
        String beginDate = null, endDate = null;
        switch (query) {
            case "today":
            case "day":
                beginDate = TimeUtils.daySkip(0);
                endDate = TimeUtils.daySkip(1);
                break;
            case "week":
                beginDate = TimeUtils.weekSkip(0);
                endDate = TimeUtils.weekSkip(1);
                break;
            case "month":
                beginDate = TimeUtils.monthSkip(0);
                endDate = TimeUtils.monthSkip(1);
                break;
            case "year":
                beginDate = TimeUtils.yearSkip(0);
                endDate = TimeUtils.yearSkip(1);
                break;
            case "yesterday":
                beginDate = TimeUtils.daySkip(-1);
                endDate = TimeUtils.daySkip(0);
                break;
            case "last_week":
                beginDate = TimeUtils.weekSkip(-1);
                endDate = TimeUtils.weekSkip(0);
                break;
            case "last_month":
                beginDate = TimeUtils.monthSkip(-1);
                endDate = TimeUtils.monthSkip(0);
                break;
            case "last_year":
                beginDate = TimeUtils.yearSkip(-1);
                endDate = TimeUtils.yearSkip(0);
                break;
        }
        result.put("status", 200);
        result.put("records", signService.getRecordsByDateWithStuID(stuID, beginDate, endDate));

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/history_time_person", method = RequestMethod.GET)
    public JSONObject histroy_time_person(@RequestParam("stuID") String stuID, @RequestParam("begin") String beginDate, @RequestParam("end") String endDate) {
        JSONObject result = new JSONObject();
        if (accountService.selectAccessTokenByStuID(stuID) == null) {
            result.put("status", 500);
            result.put("errorMessage", "该用户在系统中不存在");
            return result;
        }
        if (beginDate != null && endDate != null) {
            if (TimeUtils.checkDateFormat(beginDate) && TimeUtils.checkDateFormat(endDate)) {
                result.put("status", 200);
                result.put("records", signService.getRecordsByDate(beginDate, endDate));
            } else {
                result.put("status", 500);
                result.put("errorMessage", "輸入日期格式有誤");
            }
        } else if (beginDate == null && endDate == null) {
            result.put("status", 500);
            result.put("errorMessage", "無參數");
        } else {
            if (beginDate != null && TimeUtils.checkDateFormat(beginDate)) {
                endDate = TimeUtils.daySkip(1);
            }
            if (endDate != null && TimeUtils.checkDateFormat(endDate)) {
                beginDate = TimeUtils.daySkip(0);
            }
            result.put("status", 200);
            result.put("records", signService.getRecordsByDateWithStuID(stuID, beginDate, endDate));
        }
        return result;
    }

}

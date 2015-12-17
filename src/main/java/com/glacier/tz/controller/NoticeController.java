package com.glacier.tz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.model.Notice;
import com.glacier.tz.service.AccountService;
import com.glacier.tz.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Glacierlx on 2015/12/16.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;
    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public JSONObject addNotice(@RequestParam("accessToken")String accessToken, @RequestParam("content")String content) {
        JSONObject result = new JSONObject();
        if (accountService.getStudentByAccessToken(accessToken) == null) {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken无效");
            return result;
        }
        if (noticeService.addNotice(accessToken, content) == 0) {
            result.put("status", 500);
            result.put("errorMessage", "添加Notice失败");
            return result;
        }
        result.put("status", 200);
        return result;
    }

    @ResponseBody
    @RequestMapping("/all")
    public JSONObject listNotice(@RequestParam("accessToken")String accessToken) {
        JSONObject result = new JSONObject();
        if (accountService.getStudentByAccessToken(accessToken) == null) {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken无效");
            return result;
        }
        List<Notice> notices = noticeService.getAllNotice();
        if ( notices != null && notices.size() != 0 ) {
            JSONObject tmp = new JSONObject();
            tmp.put("tmp", notices);
            JSONArray jsonArray = tmp.getJSONArray("tmp");
            for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                tmp = (JSONObject)iterator.next();
                tmp.put("stuID", accountService.selectStuIDByAccessToken((String)tmp.remove("accessToken")));
            }
            result.put("status", 200);
            result.put("notices", jsonArray);
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "没啦！");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/section")
    public JSONObject sectionNotice(@RequestParam("accessToken")String accessToken, @RequestParam("skip")Integer skip) {
        JSONObject result = new JSONObject();
        if (accountService.getStudentByAccessToken(accessToken) == null) {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken无效");
            return result;
        }
        List<Notice> notices = noticeService.getNoticeList(skip);
        if ( notices != null && notices.size() != 0 ) {
            JSONObject tmp = new JSONObject();
            tmp.put("tmp", notices);
            JSONArray jsonArray = tmp.getJSONArray("tmp");
            for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                tmp = (JSONObject)iterator.next();
                tmp.put("stuID", accountService.selectStuIDByAccessToken((String)tmp.remove("accessToken")));
            }
            result.put("status", 200);
            result.put("notices", jsonArray);
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "没啦！");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/refresh")
    public JSONObject refreshNotice(@RequestParam("accessToken")String accessToken, @RequestParam("lastID")Integer id) {
        JSONObject result = new JSONObject();
        if (accountService.getStudentByAccessToken(accessToken) == null) {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken无效");
            return result;
        }
        List<Notice> notices = noticeService.refresh(id);
        if ( notices != null && notices.size() != 0 ) {
            JSONObject tmp = new JSONObject();
            tmp.put("tmp", notices);
            JSONArray jsonArray = tmp.getJSONArray("tmp");
            for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                tmp = (JSONObject)iterator.next();
                tmp.put("stuID", accountService.selectStuIDByAccessToken((String)tmp.remove("accessToken")));
            }
            result.put("status", 200);
            result.put("notices", jsonArray);
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "没啦！");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/section_person")
    public JSONObject sectionPersonNotice(@RequestParam("accessToken")String accessToken, @RequestParam("stuID")String stuID, @RequestParam("skip")Integer skip) {
        JSONObject result = new JSONObject();
        if (accountService.getStudentByAccessToken(accessToken) == null) {
            result.put("status", 500);
            result.put("errorMessage", "AccessToken无效");
            return result;
        }
        if (accountService.selectAccessTokenByStuID(stuID) == null) {
            result.put("status", 500);
            result.put("errorMessage", "该用户在系统中不存在");
            return result;
        }

        List<Notice> notices = noticeService.getNoticeListWithStuID(stuID, skip);
        if ( notices != null && notices.size() != 0 ) {
            JSONObject tmp = new JSONObject();
            tmp.put("tmp", notices);
            JSONArray jsonArray = tmp.getJSONArray("tmp");
            for (Iterator iterator = jsonArray.iterator(); iterator.hasNext(); ) {
                tmp = (JSONObject)iterator.next();
                tmp.put("stuID", accountService.selectStuIDByAccessToken((String)tmp.remove("accessToken")));
            }
            result.put("status", 200);
            result.put("notices", jsonArray);
        }
        else {
            result.put("status", 500);
            result.put("errorMessage", "没啦！");
        }
        return result;
    }

}

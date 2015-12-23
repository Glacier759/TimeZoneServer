package com.glacier.tz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glacier.tz.model.Notice;
import com.glacier.tz.model.Student;
import com.glacier.tz.service.AccountService;
import com.glacier.tz.service.NoticeService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Glacierlx on 2015/12/16.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private static Logger logger = Logger.getLogger(NoticeController.class);
    @Resource
    private NoticeService noticeService;
    @Resource
    private AccountService accountService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public JSONObject addNotice(@RequestParam("accessToken")String accessToken, @RequestParam("content")String content) {
        logger.info("[controller] add notice - stuID: " + accountService.selectStuIDByAccessToken(accessToken) + "\taccessToken: " + accessToken + "\tcontent: " + content);
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
    @RequestMapping(method = RequestMethod.GET)
    public JSONObject listNotice(@RequestParam("accessToken")String accessToken) {
        logger.info("[controller] get all notice - stuID: " + accountService.selectStuIDByAccessToken(accessToken) + "\taccessToken: " + accessToken);
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
                Student student = accountService.getStudentByAccessToken((String)tmp.remove("accessToken"));
                tmp.put("stuID", student.getStuId());
                tmp.put("stuName", student.getStuName());
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
    @RequestMapping(value = "/{skip}", method = RequestMethod.GET)
    public JSONObject sectionNotice(@RequestParam("accessToken")String accessToken, @PathVariable("skip")Integer skip) {
        logger.info("[controller] skip notice - stuID: " + accountService.selectStuIDByAccessToken(accessToken) + "\taccessToken: " + accessToken + "\tskip: " + skip);
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
                Student student = accountService.getStudentByAccessToken((String)tmp.remove("accessToken"));
                tmp.put("stuID", student.getStuId());
                tmp.put("stuName", student.getStuName());
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
    @RequestMapping(value = "/refresh/{lastID}",method = RequestMethod.GET)
    public JSONObject refreshNotice(@RequestParam("accessToken")String accessToken, @PathVariable("lastID")Integer id) {
        logger.info("[controller] refresh - stuID: " + accountService.selectStuIDByAccessToken(accessToken) + "\taccessToken: " + accessToken + "\tlastID: " + id);
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
                Student student = accountService.getStudentByAccessToken((String)tmp.remove("accessToken"));
                tmp.put("stuID", student.getStuId());
                tmp.put("stuName", student.getStuName());
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
    @RequestMapping(value = "/{stuID}/{skip}", method = RequestMethod.GET)
    public JSONObject sectionPersonNotice(@RequestParam("accessToken")String accessToken, @PathVariable("stuID")String stuID, @PathVariable("skip")Integer skip) {
        logger.info("[controller] person skip notice - stuID: " + stuID + "\taccessToken: " + accessToken + "\tskip: " + skip);
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
                Student student = accountService.getStudentByAccessToken((String)tmp.remove("accessToken"));
                tmp.put("stuID", student.getStuId());
                tmp.put("stuName", student.getStuName());
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

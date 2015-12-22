package com.glacier.tz.service.impl;

import com.alibaba.fastjson.JSON;
import com.glacier.tz.dao.StudentMapper;
import com.glacier.tz.model.Student;
import com.glacier.tz.service.AccountService;
import com.glacier.tz.utils.EncryptUtils;
import com.glacier.tz.utils.HttpUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Glacierlx on 2015/12/15.
 */
@Service
public class AccountServiceImpl implements AccountService {

    Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Resource
    private StudentMapper studentMapper;

    public Student login(String tid, String password) {

        Student student = null;
        HttpUtils httpUtil = new HttpUtils();
        Document document = httpUtil.getMethod("http://222.24.19.201/default4.aspx");
        //筛选需要的部分
        Element element = document.getElementById("form1");
        Elements elements = element.select("input[name=__VIEWSTATE]");
        String value = elements.attr("value");

        //将发的信息写入post请求中
        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        pairs.add(new BasicNameValuePair("__VIEWSTATE", value));
        pairs.add(new BasicNameValuePair("TextBox1", tid));
        pairs.add(new BasicNameValuePair("TextBox2", password));
        pairs.add(new BasicNameValuePair("RadioButtonList1", "\321\247\311\372"));
        pairs.add(new BasicNameValuePair("Button1", "\265\307\302\274"));
        //发送post请求
        httpUtil.postMethod("http://222.24.19.201/default4.aspx", pairs);

        document = httpUtil.getMethod("http://222.24.19.201/xs_main.aspx?xh=" + tid);
        for ( Element ele : document.select("ul[class=sub]").select("a[href]") ) {
            if ( ele.text().equals("个人信息") ) {
                document = httpUtil.getMethod(ele.attr("abs:href"));

                student = new Student();
                student.setStuId(document.getElementById("xh").text());
                student.setStuName(document.getElementById("xm").text());
                student.setStuClass(document.getElementById("lbl_xzb").text());
                student.setStuMajor(document.getElementById("lbl_zymc").text());
                student.setAccessToken(EncryptUtils.md5DEncode(EncryptUtils.base64Encode(student.getStuId())));

                Student stu = studentMapper.selectByStudentID(student.getStuId());
                if ( stu != null ) {
                    student = stu;
                    logger.info("login - " + JSON.toJSONString(student));
                }
                else {
                    student.setStuIntroduction("他还没有填写自我介绍哟~");
                    studentMapper.insert(student);
                    logger.info("new user - " + JSON.toJSONString(student));
                }
            }
        }
        return student;
    }

    public int updateIntroduction(String accessToken, String introduction) {
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("accessToken", accessToken);
        params.put("introduction", introduction);
        int status = studentMapper.updateIntroductionByAccessToken(params);
        logger.info("update introduction - access_token: " + accessToken + "\tinfo: " + introduction);
        return status;
    }

    public Student getStudentByAccessToken(String accessToken) {
        return studentMapper.selectByAccessToken(accessToken);
    }

    public List<Student> selectAllStudents() {
        return studentMapper.selectAllStudents();
    }

    public String selectAccessTokenByStuID(String stuID) {
        return studentMapper.selectAccessTokenByStuID(stuID);
    }

    public String selectStuIDByAccessToken(String accessToken) {
        return studentMapper.selectStuIDByAccessToken(accessToken);
    }

    public boolean isAccessTokenBelongStuID(String accessToken, String stuID) {
        Student student = getStudentByAccessToken(accessToken);
        if ( student != null && student.getStuId().equals(stuID) ) {
            return true;
        }
        return false;
    }

}

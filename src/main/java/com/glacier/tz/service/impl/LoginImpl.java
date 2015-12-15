package com.glacier.tz.service.impl;

import com.glacier.tz.dao.StudentMapper;
import com.glacier.tz.model.Student;
import com.glacier.tz.service.Login;
import com.glacier.tz.utils.HttpUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glacierlx on 2015/12/14.
 */
@Service
public class LoginImpl implements Login {

    private static Logger logger = Logger.getLogger(LoginImpl.class.getName());

    @Resource
    private StudentMapper studentMapper;

    public List<Student> test() {
        return studentMapper.selectAllStudents();
    }

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
            }
        }
        return student;
    }
}
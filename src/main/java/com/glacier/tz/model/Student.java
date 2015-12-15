package com.glacier.tz.model;

import java.util.Arrays;

public class Student {
    private Integer id;

    private String stuId;

    private String stuName;

    private String stuClass;

    private String stuMajor;

    private String stuIntroduction;

    private byte[] stuHead;

    private String accessToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass == null ? null : stuClass.trim();
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor == null ? null : stuMajor.trim();
    }

    public String getStuIntroduction() {
        return stuIntroduction;
    }

    public void setStuIntroduction(String stuIntroduction) {
        this.stuIntroduction = stuIntroduction == null ? null : stuIntroduction.trim();
    }

    public byte[] getStuHead() {
        return stuHead;
    }

    public void setStuHead(byte[] stuHead) {
        this.stuHead = stuHead;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuIntroduction='" + stuIntroduction + '\'' +
                ", stuHead=" + Arrays.toString(stuHead) +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
package com.glacier.tz.model;

import java.util.Arrays;

public class Student {
    private Integer id;

    private String stuId;

    private String stuName;

    private String stuClass;

    private String stuMajor;

    private String stuInformation;

    private byte[] stuHead;

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

    public String getStuInformation() {
        return stuInformation;
    }

    public void setStuInformation(String stuInformation) {
        this.stuInformation = stuInformation == null ? null : stuInformation.trim();
    }

    public byte[] getStuHead() {
        return stuHead;
    }

    public void setStuHead(byte[] stuHead) {
        this.stuHead = stuHead;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuInformation='" + stuInformation + '\'' +
                ", stuHead=" + Arrays.toString(stuHead) +
                '}';
    }
}
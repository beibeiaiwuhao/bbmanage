package com.beibei.bbmanage.vo;


import com.beibei.bbmanage.entity.TGartenStudentEntity;

public class GartenStudentFormVo {

    //所属幼儿园
    private Integer gartenId;
    //学生名字
    private String studentName;
    //学生性别
    private String studentGender;
    //分配班级
    private Integer classId;
    //学生兴趣爱好
    private String studentHobbies;
    //学生年纪
    private String studentAge;
    //学生头像
    private String studentImgUrl;
    //所报课程
    private String courseName;
    //所报课程id
    private Integer courseId;


    //家长信息是否存在
    private String isExist;
    //家长的id
    private Integer parentId;
    //家长名字
    private String parentName;
    //家长联系地址
    private String address;
    //家长联系方式
    private String parentConnect;
    //家长微信
    private String parentWechat;
    //家长头像
    private String parentImgUrl;



    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }


    public String getStudentHobbies() {
        return studentHobbies;
    }

    public void setStudentHobbies(String studentHobbies) {
        this.studentHobbies = studentHobbies;
    }

    public String getIsExist() {
        return isExist;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }



    public String getParentConnect() {
        return parentConnect;
    }

    public void setParentConnect(String parentConnect) {
        this.parentConnect = parentConnect;
    }

    public String getParentWechat() {
        return parentWechat;
    }

    public void setParentWechat(String parentWechat) {
        this.parentWechat = parentWechat;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGartenId() {
        return gartenId;
    }

    public void setGartenId(Integer gartenId) {
        this.gartenId = gartenId;
    }


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getStudentImgUrl() {
        return studentImgUrl;
    }

    public void setStudentImgUrl(String studentImgUrl) {
        this.studentImgUrl = studentImgUrl;
    }

    public String getParentImgUrl() {
        return parentImgUrl;
    }

    public void setParentImgUrl(String parentImgUrl) {
        this.parentImgUrl = parentImgUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}

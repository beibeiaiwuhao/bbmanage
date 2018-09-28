package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_teacher", schema = "bbmanage", catalog = "")
public class TGartenTeacherEntity {
    private Integer id;
    private Integer gartenId;
    private String teacherName;
    private String teacherMobile;
    private String teacherClasses;
    private String teacherDesc;
    private String wechat;
    private String address;
    private String avatarImgUrl;
    private Integer status;
    private Integer courseId;
    private Integer gender;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "garten_id")
    public Integer getGartenId() {
        return gartenId;
    }

    public void setGartenId(Integer gartenId) {
        this.gartenId = gartenId;
    }

    @Basic
    @Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "teacher_mobile")
    public String getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }

    @Basic
    @Column(name = "teacher_classes")
    public String getTeacherClasses() {
        return teacherClasses;
    }

    public void setTeacherClasses(String teacherClasses) {
        this.teacherClasses = teacherClasses;
    }

    @Basic
    @Column(name = "teacher_desc")
    public String getTeacherDesc() {
        return teacherDesc;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    @Basic
    @Column(name = "wechat")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "avatar_img_url")
    public String getAvatarImgUrl() {
        return avatarImgUrl;
    }

    public void setAvatarImgUrl(String avatarImgUrl) {
        this.avatarImgUrl = avatarImgUrl;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TGartenTeacherEntity that = (TGartenTeacherEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gartenId, that.gartenId) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(teacherMobile, that.teacherMobile) &&
                Objects.equals(teacherClasses, that.teacherClasses) &&
                Objects.equals(teacherDesc, that.teacherDesc) &&
                Objects.equals(wechat, that.wechat) &&
                Objects.equals(address, that.address) &&
                Objects.equals(avatarImgUrl, that.avatarImgUrl) &&
                Objects.equals(status, that.status) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gartenId, teacherName, teacherMobile, teacherClasses, teacherDesc, wechat, address, avatarImgUrl, status,courseId,gender);
    }

    @Basic
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "gender")
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}

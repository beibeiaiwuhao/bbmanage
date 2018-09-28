package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_student", schema = "bbmanage", catalog = "")
public class TGartenStudentEntity {
    private Integer id;
    private String studentName;
    private String studentMobile;
    private String studentAge;
    private String studentDesc;
    private String address;
    private String avatarImgUrl;
    private Integer status;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "student_mobile")
    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    @Basic
    @Column(name = "student_age")
    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    @Basic
    @Column(name = "student_desc")
    public String getStudentDesc() {
        return studentDesc;
    }

    public void setStudentDesc(String studentDesc) {
        this.studentDesc = studentDesc;
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
        TGartenStudentEntity that = (TGartenStudentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(studentName, that.studentName) &&
                Objects.equals(studentMobile, that.studentMobile) &&
                Objects.equals(studentAge, that.studentAge) &&
                Objects.equals(studentDesc, that.studentDesc) &&
                Objects.equals(address, that.address) &&
                Objects.equals(avatarImgUrl, that.avatarImgUrl) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, studentName, studentMobile, studentAge, studentDesc, address, avatarImgUrl, status);
    }
}

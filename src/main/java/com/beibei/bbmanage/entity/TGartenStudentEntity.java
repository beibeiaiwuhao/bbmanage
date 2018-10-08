package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_student", schema = "bbmanage", catalog = "")
public class TGartenStudentEntity {
    private Integer id;
    private String studentName;
    private String studentGender;
    private String className;
    private String courseName;
    private String studentAge;
    private String studentDesc;
    private String avatarImgUrl;
    private Integer status;
    private Integer gartenId;

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
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
                Objects.equals(studentAge, that.studentAge) &&
                Objects.equals(studentDesc, that.studentDesc) &&
                Objects.equals(avatarImgUrl, that.avatarImgUrl) &&
                Objects.equals(studentGender, that.studentGender) &&
                Objects.equals(className, that.className) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, studentName, studentAge, studentDesc, avatarImgUrl, status,studentGender,className,courseName);
    }

    @Basic
    @Column(name = "student_gender")
    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    @Basic
    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "garten_id")
    public Integer getGartenId() {
        return gartenId;
    }

    public void setGartenId(Integer gartenId) {
        this.gartenId = gartenId;
    }
}

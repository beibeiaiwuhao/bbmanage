package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_photos", schema = "bbmanage", catalog = "")
public class TGartenPhotosEntity {
    private Integer id;
    private String photoName;
    private String photoDes;
    private String photoUrl;
    private String photoSize;
    private String creator;
    private String createTime;
    private String mender;
    private String mendTime;
    private String remark;
    private Integer classId;
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
    @Column(name = "photo_name")
    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Basic
    @Column(name = "photo_des")
    public String getPhotoDes() {
        return photoDes;
    }

    public void setPhotoDes(String photoDes) {
        this.photoDes = photoDes;
    }

    @Basic
    @Column(name = "photo_url")
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Basic
    @Column(name = "photo_size")
    public String getPhotoSize() {
        return photoSize;
    }

    public void setPhotoSize(String photoSize) {
        this.photoSize = photoSize;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "mender")
    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
        this.mender = mender;
    }

    @Basic
    @Column(name = "mend_time")
    public String getMendTime() {
        return mendTime;
    }

    public void setMendTime(String mendTime) {
        this.mendTime = mendTime;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TGartenPhotosEntity that = (TGartenPhotosEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(photoName, that.photoName) &&
                Objects.equals(photoDes, that.photoDes) &&
                Objects.equals(photoUrl, that.photoUrl) &&
                Objects.equals(photoSize, that.photoSize) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, photoName, photoDes, photoUrl, photoSize, creator, createTime, mender, mendTime, remark);
    }

    @Basic
    @Column(name = "class_id")
    public Integer getClassId() {
        return classId;
    }
    public void setClassId(Integer classId) {
        this.classId = classId;
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

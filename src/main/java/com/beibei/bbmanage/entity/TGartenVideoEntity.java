package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_video", schema = "bbmanage", catalog = "")
public class TGartenVideoEntity {
    private Integer id;
    private Integer gartenId;
    private String videoName;
    private String videoDesc;
    private String videoUrl;
    private String videoType;
    private String videoSize;
    private String videoClasses;
    private Integer status;
    private String creator;
    private String createTime;
    private String mender;
    private String mendTime;
    private String remark;
    private Integer classId;

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
    @Column(name = "video_name")
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "video_desc")
    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    @Basic
    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "video_type")
    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    @Basic
    @Column(name = "video_size")
    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    @Basic
    @Column(name = "video_classes")
    public String getVideoClasses() {
        return videoClasses;
    }

    public void setVideoClasses(String videoClasses) {
        this.videoClasses = videoClasses;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Basic
    @Column(name = "class_id")
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TGartenVideoEntity that = (TGartenVideoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gartenId, that.gartenId) &&
                Objects.equals(videoName, that.videoName) &&
                Objects.equals(videoDesc, that.videoDesc) &&
                Objects.equals(videoUrl, that.videoUrl) &&
                Objects.equals(videoType, that.videoType) &&
                Objects.equals(videoSize, that.videoSize) &&
                Objects.equals(videoClasses, that.videoClasses) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gartenId, videoName,classId, videoDesc, videoUrl, videoType, videoSize, videoClasses, status, creator, createTime, mender, mendTime, remark);
    }


}

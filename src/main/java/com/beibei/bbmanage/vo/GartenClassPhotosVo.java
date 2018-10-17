package com.beibei.bbmanage.vo;

import java.math.BigInteger;

public class GartenClassPhotosVo {

    private Integer classId;
    private String className;
    private String photoUrl;
    private String createTime;
    private BigInteger imgCount;


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public BigInteger getImgCount() {
        return imgCount;
    }

    public void setImgCount(BigInteger imgCount) {
        this.imgCount = imgCount;
    }
}

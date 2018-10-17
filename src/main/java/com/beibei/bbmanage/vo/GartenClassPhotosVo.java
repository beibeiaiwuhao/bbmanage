package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TGartenInfoEntity;

import java.math.BigInteger;
import java.util.List;

public class GartenClassPhotosVo {

    private Integer classId;
    private String className;
    private String photoUrl;
    private String createTime;
    private BigInteger imgCount;

    private String showTime;
    private List<TGartenInfoEntity> phototList;



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


    public String getImgCount() {
        return imgCount.toString();
    }

    public void setImgCount(BigInteger imgCount) {
        this.imgCount = imgCount;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public List<TGartenInfoEntity> getPhototList() {
        return phototList;
    }

    public void setPhototList(List<TGartenInfoEntity> phototList) {
        this.phototList = phototList;
    }
}

package com.beibei.bbmanage.vo;

import java.math.BigInteger;

/**
 * @author haohao
 * <p>
 * date: 2018年11月06日
 */
public class ActivityPhotosFileVo  {

    private Integer activityId;
    private String activityName;
    private String fileUrl;
    private Integer fileId;
    private String fileType;
    private Double fileSize;
    private String createTime;
    private BigInteger imgCount;



    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getImgCount() {
            return imgCount != null?imgCount.toString():"0";
    }

    public void setImgCount(BigInteger imgCount) {
        this.imgCount = imgCount;
    }
}

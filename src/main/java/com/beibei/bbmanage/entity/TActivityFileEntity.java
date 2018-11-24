package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @author haohao
 * <p>
 * date: 2018年11月06日
 */
@Entity
@Table(name = "t_activity_file", schema = "bbmanage", catalog = "")
public class TActivityFileEntity {
    private Integer id;
    private String fileUrl;
    private String fileName;
    private String fileType;
    private Double fileSize;
    private Integer status;
    private String creator;
    private String createTime;
    private String mender;
    private String mendTime;
    private String remark;


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
    @Column(name = "file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Basic
    @Column(name = "file_size")
    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TActivityFileEntity that = (TActivityFileEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fileUrl, that.fileUrl) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(fileSize, that.fileSize) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fileUrl, fileName, fileType, fileSize, status, creator, createTime, mender, mendTime, remark);
    }


}

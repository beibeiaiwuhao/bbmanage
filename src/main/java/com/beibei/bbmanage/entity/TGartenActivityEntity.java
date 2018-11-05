package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_activity", schema = "bbmanage", catalog = "")
public class TGartenActivityEntity {
    private Integer id;
    private Integer gartenId;
    private String activityName;
    private String activityDesc;
    private Integer status;
    private String creator;
    private String createTime;
    private String mender;
    private String mendTime;
    private String remark;
    private String activityCharge;

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
    @Column(name = "activity_name")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Basic
    @Column(name = "activity_desc")
    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
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
        TGartenActivityEntity that = (TGartenActivityEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gartenId, that.gartenId) &&
                Objects.equals(activityName, that.activityName) &&
                Objects.equals(activityDesc, that.activityDesc) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gartenId, activityName, activityDesc, status, creator, createTime, mender, mendTime, remark);
    }

    @Basic
    @Column(name = "activity_charge")
    public String getActivityCharge() {
        return activityCharge;
    }

    public void setActivityCharge(String activityCharge) {
        this.activityCharge = activityCharge;
    }
}

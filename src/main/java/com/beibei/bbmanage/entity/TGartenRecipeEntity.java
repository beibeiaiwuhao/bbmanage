package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_recipe", schema = "bbmanage", catalog = "")
public class TGartenRecipeEntity {
    private Integer id;
    private Integer gartenId;
    private String recipeDate;
    private String mealName;
    private String mealDesc;
    private String avatarImgUrl;
    private String weekName;
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
    @Column(name = "garten_id")
    public Integer getGartenId() {
        return gartenId;
    }

    public void setGartenId(Integer gartenId) {
        this.gartenId = gartenId;
    }

    @Basic
    @Column(name = "recipe_date")
    public String getRecipeDate() {
        return recipeDate;
    }

    public void setRecipeDate(String recipeDate) {
        this.recipeDate = recipeDate;
    }

    @Basic
    @Column(name = "meal_name")
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    @Basic
    @Column(name = "meal_desc")
    public String getMealDesc() {
        return mealDesc;
    }

    public void setMealDesc(String mealDesc) {
        this.mealDesc = mealDesc;
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
    @Column(name = "week_name")
    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
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
        TGartenRecipeEntity that = (TGartenRecipeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gartenId, that.gartenId) &&
                Objects.equals(recipeDate, that.recipeDate) &&
                Objects.equals(mealName, that.mealName) &&
                Objects.equals(mealDesc, that.mealDesc) &&
                Objects.equals(avatarImgUrl, that.avatarImgUrl) &&
                Objects.equals(weekName, that.weekName) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gartenId, recipeDate, mealName, mealDesc, avatarImgUrl, weekName, status, creator, createTime, mender, mendTime, remark);
    }
}

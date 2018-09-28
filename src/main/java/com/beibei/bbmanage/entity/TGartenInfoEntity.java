package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_garten_info", schema = "bbmanage", catalog = "")
public class TGartenInfoEntity {
    private Integer id;
    private String gardenName;
    private String gardenAddress;
    private String gardenPhone;
    private String gardenHead;
    private String gardenConcept;
    private Integer sort;
    private String pictureUrl;
    private Integer status;
    private String creator;
    private String createTime;
    private String mender;
    private String mendTime;
    private String remark;
    private String city;
    private String province;
    private String area;

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
    @Column(name = "garden_name")
    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    @Basic
    @Column(name = "garden_address")
    public String getGardenAddress() {
        return gardenAddress;
    }

    public void setGardenAddress(String gardenAddress) {
        this.gardenAddress = gardenAddress;
    }

    @Basic
    @Column(name = "garden_phone")
    public String getGardenPhone() {
        return gardenPhone;
    }

    public void setGardenPhone(String gardenPhone) {
        this.gardenPhone = gardenPhone;
    }

    @Basic
    @Column(name = "garden_head")
    public String getGardenHead() {
        return gardenHead;
    }

    public void setGardenHead(String gardenHead) {
        this.gardenHead = gardenHead;
    }

    @Basic
    @Column(name = "garden_concept")
    public String getGardenConcept() {
        return gardenConcept;
    }

    public void setGardenConcept(String gardenConcept) {
        this.gardenConcept = gardenConcept;
    }

    @Basic
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "picture_url")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
        TGartenInfoEntity that = (TGartenInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gardenName, that.gardenName) &&
                Objects.equals(gardenAddress, that.gardenAddress) &&
                Objects.equals(gardenPhone, that.gardenPhone) &&
                Objects.equals(gardenHead, that.gardenHead) &&
                Objects.equals(gardenConcept, that.gardenConcept) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(pictureUrl, that.pictureUrl) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(remark, that.remark)&&
                Objects.equals(city, that.city) &&
                Objects.equals(province, that.province) &&
                Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gardenName, gardenAddress, gardenPhone, gardenHead, gardenConcept, sort, pictureUrl, status, creator, createTime, mender, mendTime, remark,city,province,area);
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

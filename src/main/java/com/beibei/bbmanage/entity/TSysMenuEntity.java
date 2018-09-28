package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_sys_menu", schema = "bbmanage", catalog = "")
public class TSysMenuEntity {
    private Integer menuId;
    private String menuCode;
    private String menuName;
    private String parentMenuCode;
    private String menuPageUrl;
    private Integer menuLevel;
    private Integer isVisible;
    private String cssClass;
    private Integer sysCode;
    private Integer sort;
    private String creator;
    private String createTime;
    private String mender;
    private String mendTime;
    private String remark;

    @Id
    @Column(name = "menu_id")
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_code")
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "parent_menu_code")
    public String getParentMenuCode() {
        return parentMenuCode;
    }

    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    @Basic
    @Column(name = "menu_page_url")
    public String getMenuPageUrl() {
        return menuPageUrl;
    }

    public void setMenuPageUrl(String menuPageUrl) {
        this.menuPageUrl = menuPageUrl;
    }

    @Basic
    @Column(name = "menu_level")
    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    @Basic
    @Column(name = "is_visible")
    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    @Basic
    @Column(name = "css_class")
    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @Basic
    @Column(name = "sys_code")
    public Integer getSysCode() {
        return sysCode;
    }

    public void setSysCode(Integer sysCode) {
        this.sysCode = sysCode;
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
        TSysMenuEntity that = (TSysMenuEntity) o;
        return Objects.equals(menuId, that.menuId) &&
                Objects.equals(menuCode, that.menuCode) &&
                Objects.equals(menuName, that.menuName) &&
                Objects.equals(parentMenuCode, that.parentMenuCode) &&
                Objects.equals(menuPageUrl, that.menuPageUrl) &&
                Objects.equals(menuLevel, that.menuLevel) &&
                Objects.equals(isVisible, that.isVisible) &&
                Objects.equals(cssClass, that.cssClass) &&
                Objects.equals(sysCode, that.sysCode) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(mender, that.mender) &&
                Objects.equals(mendTime, that.mendTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, menuCode, menuName, parentMenuCode, menuPageUrl, menuLevel, isVisible, cssClass, sysCode, sort, creator, createTime, mender, mendTime, remark);
    }
}

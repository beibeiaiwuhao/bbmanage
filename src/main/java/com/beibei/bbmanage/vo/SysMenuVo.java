package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TSysMenuEntity;

import java.util.List;

public class SysMenuVo extends TSysMenuEntity {

    private List<SysMenuVo> secondMenuArr;

    public SysMenuVo(TSysMenuEntity menuEntity) {
        this.setMenuId(menuEntity.getMenuId());
        this.setMenuCode(menuEntity.getMenuCode());
        this.setMenuName(menuEntity.getMenuName());
        this.setParentMenuCode(menuEntity.getParentMenuCode());
        this.setMenuPageUrl(menuEntity.getMenuPageUrl());
        this.setMenuLevel(menuEntity.getMenuLevel());
        this.setIsVisible(menuEntity.getIsVisible());
        this.setCssClass(menuEntity.getCssClass());
        this.setSysCode(menuEntity.getSysCode());
        this.setSort(menuEntity.getSort());
        this.setCreator(menuEntity.getCreator());
        this.setCreateTime(menuEntity.getCreateTime());
        this.setMender(menuEntity.getMender());
        this.setMendTime(menuEntity.getMendTime());
        this.setRemark(menuEntity.getRemark());

    }


    public List<SysMenuVo> getSecondMenuArr() {
        return secondMenuArr;
    }

    public void setSecondMenuArr(List<SysMenuVo> secondMenuArr) {
        this.secondMenuArr = secondMenuArr;
    }
}

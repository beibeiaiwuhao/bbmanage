package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TGartenNoticeEntity;

public class GartenNoticeInfoVo extends TGartenNoticeEntity {

    private String gardenName;

    public GartenNoticeInfoVo(TGartenNoticeEntity entity) {
        this.setNoticeId(entity.getNoticeId());
        this.setTitle(this.getTitle());
        this.setContent(this.getContent());
        this.setPublicStatus(this.getPublicStatus());
        this.setCreator(this.getCreator());
        this.setCreateTime(this.getCreateTime());
        this.setMender(this.getMender());
        this.setMendTime(this.getMendTime());
        this.setRemark(this.getRemark());
    }

    public GartenNoticeInfoVo(){

    }

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }
}

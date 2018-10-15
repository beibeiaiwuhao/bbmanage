package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TGartenVideoEntity;

public class GartenVideoVo extends TGartenVideoEntity {

    private String gartenName;
    private String className;


    public String getGartenName() {
        return gartenName;
    }

    public void setGartenName(String gartenName) {
        this.gartenName = gartenName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

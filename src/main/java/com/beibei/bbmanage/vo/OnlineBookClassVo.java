package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TUserInfoEntity;

/**
 * @author haohao
 * <p>
 * date: 2018年11月01日
 */
public class OnlineBookClassVo extends TUserInfoEntity {

    private String apponitmentTime;
    private String apponitmentClasses;


    public String getApponitmentTime() {
        return apponitmentTime;
    }

    public void setApponitmentTime(String apponitmentTime) {
        this.apponitmentTime = apponitmentTime;
    }

    public String getApponitmentClasses() {
        return apponitmentClasses;
    }

    public void setApponitmentClasses(String apponitmentClasses) {
        this.apponitmentClasses = apponitmentClasses;
    }
}

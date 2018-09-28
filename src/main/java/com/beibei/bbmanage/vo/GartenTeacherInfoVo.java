package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TGartenTeacherEntity;

/**
 * 添加园所老师的vo
 */
public class GartenTeacherInfoVo extends TGartenTeacherEntity {

    //选择的班级id
    private Integer classId;


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}

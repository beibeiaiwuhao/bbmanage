package com.beibei.bbmanage.vo;

import com.beibei.bbmanage.entity.TGartenTeacherEntity;

/**
 * 添加园所老师的vo
 */
public class GartenTeacherInfoVo extends TGartenTeacherEntity {

    //选择的班级id
    private Integer classId;

    //班级名字
    private String className;


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() { return className; }

    public void setClassName(String className) { this.className = className; }
}

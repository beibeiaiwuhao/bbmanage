package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenClassEntity;

import java.util.List;

/**
 * 园所班级表
 */
public interface GartenClassesService {

    public List<TGartenClassEntity> getGartenClassesWithGartenId(Integer gartenId);

}

package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenClassEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 园所班级表
 */
public interface GartenClassesService {

    public List<TGartenClassEntity> getGartenClassesWithGartenId(Integer gartenId);

    public void saveGartenClasses(TGartenClassEntity entity);

    public Page<TGartenClassEntity> getPageGartenClassWitQueryCondition(Map<String,Object> querContidion);

}

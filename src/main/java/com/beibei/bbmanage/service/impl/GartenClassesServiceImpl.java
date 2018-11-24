package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TGartenClassEntity;
import com.beibei.bbmanage.repository.GartenClassesRepository;
import com.beibei.bbmanage.service.GartenClassesService;
import com.beibei.bbmanage.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GartenClassesServiceImpl implements GartenClassesService {

    @Autowired
    private GartenClassesRepository gartenClassesRepository;


    @Override
    public List<TGartenClassEntity> getGartenClassesWithGartenId(Integer gartenId) {
        List<TGartenClassEntity> gartenClassEntities = gartenClassesRepository.findAllByGartenId(gartenId);
        return gartenClassEntities;
    }

    @Override
    @Transactional
    public void saveGartenClasses(TGartenClassEntity entity) {

        entity.setCreator("system");
        entity.setCreateTime(DateUtil.formatDateTime(new Date()));
        gartenClassesRepository.save(entity);
    }

    @Override
    public Page<TGartenClassEntity> getPageGartenClassWitQueryCondition(Map<String, Object> queryCondition) {
        Integer limit = Integer.parseInt((String) queryCondition.get("limit"));
        Integer page = Integer.parseInt((String) queryCondition.get("page"));
        Integer gartenId = Integer.parseInt((String) queryCondition.get("gartenId"));
        Pageable pageable = new PageRequest(page,limit);
        Page<TGartenClassEntity> repositoryAll = gartenClassesRepository.findAllByGartenId(gartenId,pageable);
        return repositoryAll;
    }

    @Override
    public List<TGartenClassEntity> getGartenClassesWithUserId(Integer userId) {



        return null;
    }
}

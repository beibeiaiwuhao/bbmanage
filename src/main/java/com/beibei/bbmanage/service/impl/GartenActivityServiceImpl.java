package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TGartenActivityEntity;
import com.beibei.bbmanage.repository.GartenActivityRepository;
import com.beibei.bbmanage.service.GartenActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */

@Service
public class GartenActivityServiceImpl implements GartenActivityService {

    @Autowired
    private GartenActivityRepository gartenActivityRepository;

    @Override
    public Page<TGartenActivityEntity> findAll(Integer page,Integer size) {
        Pageable pageable = new PageRequest(page,size);
        return gartenActivityRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void saveGartenActivity(TGartenActivityEntity entity) {
        gartenActivityRepository.save(entity);
    }
}

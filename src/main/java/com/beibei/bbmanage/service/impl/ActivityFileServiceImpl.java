package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TActivityFileEntity;
import com.beibei.bbmanage.repository.ActivityFileRepository;
import com.beibei.bbmanage.service.ActivityFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author haohao
 * <p>
 * date: 2018年11月06日
 */
@Service
public class ActivityFileServiceImpl implements ActivityFileService {

    @Autowired
    private ActivityFileRepository activityFileRepository;

    @Override
    @Transactional
    public void saveActivityFile(TActivityFileEntity entity) {
        activityFileRepository.save(entity);
    }
}

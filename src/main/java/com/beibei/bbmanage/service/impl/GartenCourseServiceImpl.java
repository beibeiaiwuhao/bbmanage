package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TGartenCourseEntity;
import com.beibei.bbmanage.repository.GartenCourseRepository;
import com.beibei.bbmanage.service.GartenCourseService;
import com.beibei.bbmanage.utils.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haohao
 * <p>
 * date: 2018年10月31日
 */
@Service
public class GartenCourseServiceImpl implements GartenCourseService {

    @Autowired
    private GartenCourseRepository gartenCourseRepository;


    @Override
    public List<TGartenCourseEntity> getGartenCourseList() {
        List<TGartenCourseEntity> courseEntities = IteratorUtils.copyIterator(gartenCourseRepository.findAll().iterator());
        return courseEntities;
    }
}

package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TGartenClassEntity;
import com.beibei.bbmanage.repository.GartenClassesRepository;
import com.beibei.bbmanage.service.GartenClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GartenClassesServiceImpl implements GartenClassesService {

    @Autowired
    private GartenClassesRepository gartenClassesRepository;


    @Override
    public List<TGartenClassEntity> getGartenClassesWithGartenId(Integer gartenId) {
        List<TGartenClassEntity> gartenClassEntities = gartenClassesRepository.findAllByGartenId(gartenId);
        return gartenClassEntities;
    }
}

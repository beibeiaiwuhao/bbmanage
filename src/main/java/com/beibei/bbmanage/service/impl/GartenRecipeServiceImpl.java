package com.beibei.bbmanage.service.impl;


import com.beibei.bbmanage.entity.TGartenRecipeEntity;
import com.beibei.bbmanage.repository.GartenRecipeRepository;
import com.beibei.bbmanage.service.GartenRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GartenRecipeServiceImpl implements GartenRecipeService {

    @Autowired
    private GartenRecipeRepository gartenRecipeRepository;

    @Override
    @Transactional
    public void saveRecipe(TGartenRecipeEntity entity) {
        gartenRecipeRepository.save(entity);
    }
}

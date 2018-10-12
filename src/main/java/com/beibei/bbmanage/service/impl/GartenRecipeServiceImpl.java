package com.beibei.bbmanage.service.impl;


import com.beibei.bbmanage.entity.TGartenRecipeEntity;
import com.beibei.bbmanage.repository.GartenRecipeRepository;
import com.beibei.bbmanage.service.GartenRecipeService;
import com.beibei.bbmanage.utils.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GartenRecipeServiceImpl implements GartenRecipeService {

    @Autowired
    private GartenRecipeRepository gartenRecipeRepository;

    @Override
    @Transactional
    public void saveRecipe(TGartenRecipeEntity entity) {
        entity.setStatus(0);
        gartenRecipeRepository.save(entity);
    }

    @Override
    public List<TGartenRecipeEntity> findAllRecipes() {

        List<TGartenRecipeEntity> recipeEntities = IteratorUtils.copyIterator(gartenRecipeRepository.findAll().iterator());
        return recipeEntities;
    }

    @Override
    public TGartenRecipeEntity findOneRecipe(Integer id) {
        return gartenRecipeRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteSlectedRecipe(Integer id) {
        gartenRecipeRepository.delete(id);
    }
}

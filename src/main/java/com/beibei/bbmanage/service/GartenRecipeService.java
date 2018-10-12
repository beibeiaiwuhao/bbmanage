package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenRecipeEntity;

import java.util.List;

/**
 * 园所食谱
 */
public interface GartenRecipeService {

    /**
     * 保存食谱
     * @param entity
     */
    public void saveRecipe(TGartenRecipeEntity entity);

    /**
     * 查询所有的食谱
     * @return
     */
    public List<TGartenRecipeEntity> findAllRecipes();

    /**
     * 根据id 查询食谱
     * @param id
     * @return
     */
    public TGartenRecipeEntity findOneRecipe(Integer id);

    /**
     * 删除选择的食谱
     * @param id
     */
    public void deleteSlectedRecipe(Integer id);


}

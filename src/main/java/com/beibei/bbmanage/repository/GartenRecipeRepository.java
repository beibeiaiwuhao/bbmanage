package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenRecipeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenRecipeRepository extends CrudRepository<TGartenRecipeEntity,Integer> {


    /**
     * 根据幼儿园id获取食谱列表
     * @param gartenId
     * @return
     */
    List<TGartenRecipeEntity> findAllByGartenId(Integer gartenId);


}

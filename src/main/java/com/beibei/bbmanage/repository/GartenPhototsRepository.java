package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenPhotosEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenPhototsRepository extends CrudRepository<TGartenPhotosEntity,Integer> {


    List<TGartenPhotosEntity> findTGartenPhotosEntitiesByClassId(Integer classId);

}

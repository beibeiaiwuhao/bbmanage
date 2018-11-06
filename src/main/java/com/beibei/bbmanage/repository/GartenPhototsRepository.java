package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenPhotosEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenPhototsRepository extends CrudRepository<TGartenPhotosEntity,Integer> {


    List<TGartenPhotosEntity> findTGartenPhotosEntitiesByClassId(Integer classId);

    /**
     * 查询当前日期下的照片
     * @param createtime
     * @return
     */
    List<TGartenPhotosEntity> findTGartenPhotosEntitiesByCreateTimeLike(String createtime);

    int countAllByGartenId(Integer gartenId);
}

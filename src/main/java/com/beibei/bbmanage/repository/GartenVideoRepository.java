package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenVideoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenVideoRepository extends CrudRepository<TGartenVideoEntity,Integer> {

    List<TGartenVideoEntity> findTGartenVideoEntitiesByGartenIdOrderByCreateTimeDesc(Integer gartenId);

}

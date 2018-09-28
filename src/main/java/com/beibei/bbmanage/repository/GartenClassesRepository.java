package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenClassEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenClassesRepository extends CrudRepository<TGartenClassEntity,Integer> {


    public List<TGartenClassEntity> findAllByGartenId(Integer gartenId);

}

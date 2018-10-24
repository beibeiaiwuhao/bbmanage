package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenClassEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Map;

public interface GartenClassesRepository extends CrudRepository<TGartenClassEntity,Integer> {


    public List<TGartenClassEntity> findAllByGartenId(Integer gartenId);

    Page<TGartenClassEntity> findAllByGartenId(Integer gartenId ,Pageable pageable);

}

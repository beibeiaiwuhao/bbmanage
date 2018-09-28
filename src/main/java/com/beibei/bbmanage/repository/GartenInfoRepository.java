package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenInfoRepository extends CrudRepository<TGartenInfoEntity,Integer> {

    /**
     * 简单分页查询
     * @param pageable
     * @return
     */
    Page<TGartenInfoEntity> findAll(Pageable pageable);


    /**
     * 查询全部的园所信息
     */
    List<TGartenInfoEntity> findAllByStatus(Integer status);



}

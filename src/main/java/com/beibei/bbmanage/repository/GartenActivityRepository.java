package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenActivityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;




/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */
public interface GartenActivityRepository extends CrudRepository<TGartenActivityEntity,Integer> {

    Page<TGartenActivityEntity> findAll(Pageable pageable);

}

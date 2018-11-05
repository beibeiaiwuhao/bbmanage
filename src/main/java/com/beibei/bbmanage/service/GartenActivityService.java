package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenActivityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */
public interface GartenActivityService {

    Page<TGartenActivityEntity> findAll(Integer page,Integer size);

    void saveGartenActivity(TGartenActivityEntity entity);

}

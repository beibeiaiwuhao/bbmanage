package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TGartenNoticeEntity;
import com.beibei.bbmanage.vo.GartenNoticeInfoVo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GartenNoticeRepository extends CrudRepository<TGartenNoticeEntity,Integer> {


    List<TGartenNoticeEntity> findTGartenNoticeEntitiesByGartenId(Integer gartenId);

}

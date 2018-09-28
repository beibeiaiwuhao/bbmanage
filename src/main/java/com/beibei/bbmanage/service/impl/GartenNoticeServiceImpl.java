package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.GartenNoticeDao;
import com.beibei.bbmanage.entity.TGartenNoticeEntity;
import com.beibei.bbmanage.repository.GartenNoticeRepository;
import com.beibei.bbmanage.service.GartenNoticeService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.vo.GartenNoticeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class GartenNoticeServiceImpl implements GartenNoticeService {

    @Autowired
    private GartenNoticeRepository gartenNoticeRepository;

    @Autowired
    private DaoUtil daoUtil;

    @Override
    @Transactional
    public void saveGardenNotice(TGartenNoticeEntity entity) {
        //补全所有属性
        String currentTime = DateUtil.formatDateTime(new Date());
        entity.setCreateTime(currentTime);
        entity.setMendTime(currentTime);

        //发布状态(0:未发布 1:发布)
        entity.setPublicStatus(1);

        gartenNoticeRepository.save(entity);
    }

    @Override
    public Page<GartenNoticeInfoVo> findAllGardenNotice(String minDate, String maxDate, Integer gardenId, Integer page, Integer size) {
        String sql = GartenNoticeDao.getGartenNoticeWithDate(minDate,maxDate,gardenId);
        Page<GartenNoticeInfoVo> resultList = daoUtil.getPagerResultList(sql, page, size, GartenNoticeInfoVo.class);
        return resultList;
    }
}

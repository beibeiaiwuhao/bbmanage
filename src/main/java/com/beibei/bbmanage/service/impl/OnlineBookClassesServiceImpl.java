package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.onlineBook.OnLineBookClassesDao;
import com.beibei.bbmanage.entity.TAppointmentInfoEntity;
import com.beibei.bbmanage.service.OnlineBookClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class OnlineBookClassesServiceImpl implements OnlineBookClassesService {

    @Autowired
    private DaoUtil daoUtil;

    @Override
    public Page<TAppointmentInfoEntity> getOnlineBookClassesListWithDateAndUserName(String minDate, String maxDate, String userName, Integer page, Integer size) {
        String sql = OnLineBookClassesDao.getOnlineBookClassesListWithDateAndUserName(minDate,maxDate,userName,page,size);
//        List<TAppointmentInfoEntity> appointmentInfoEntities = daoUtil.getResultList(sql, TAppointmentInfoEntity.class);
        Page<TAppointmentInfoEntity> resultList = daoUtil.getPagerResultList(sql, page, size, TAppointmentInfoEntity.class);
        return resultList;
    }
}

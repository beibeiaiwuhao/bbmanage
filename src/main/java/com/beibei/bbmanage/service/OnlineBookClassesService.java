package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TAppointmentInfoEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 在线预约课程
 */
public interface OnlineBookClassesService {

    public Page<TAppointmentInfoEntity> getOnlineBookClassesListWithDateAndUserName(String minDate, String maxDate, String userName, Integer page, Integer size);
}

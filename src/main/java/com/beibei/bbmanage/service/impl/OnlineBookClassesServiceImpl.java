package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.onlineBook.OnLineBookClassesDao;
import com.beibei.bbmanage.entity.TAppointmentInfoEntity;
import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.repository.OnLineBookClassesRepository;
import com.beibei.bbmanage.repository.UserInfoRepository;
import com.beibei.bbmanage.service.OnlineBookClassesService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.SHA1;
import com.beibei.bbmanage.vo.OnlineBookClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OnlineBookClassesServiceImpl implements OnlineBookClassesService {

    @Autowired
    private DaoUtil daoUtil;

    @Autowired
    private OnLineBookClassesRepository onLineBookClassesRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Page<TAppointmentInfoEntity> getOnlineBookClassesListWithDateAndUserName(String minDate, String maxDate, String userName, Integer page, Integer size) {
        String sql = OnLineBookClassesDao.getOnlineBookClassesListWithDateAndUserName(minDate,maxDate,userName,page,size);
//        List<TAppointmentInfoEntity> appointmentInfoEntities = daoUtil.getResultList(sql, TAppointmentInfoEntity.class);
        Page<TAppointmentInfoEntity> resultList = daoUtil.getPagerResultList(sql, page, size, TAppointmentInfoEntity.class);
        return resultList;
    }


    @Override
    @Transactional
    public TAppointmentInfoEntity saveOnlineClassInfo(OnlineBookClassVo infoVo) {

        //保存微信用户到用户表
        TUserInfoEntity userInfoEntity = new TUserInfoEntity();
        userInfoEntity.setAddress(infoVo.getAddress());
        userInfoEntity.setAvatarImgUrl(infoVo.getAvatarImgUrl());
        userInfoEntity.setMobile(infoVo.getMobile());
        userInfoEntity.setUserName(infoVo.getUserName());
        userInfoEntity.setSex(infoVo.getSex());
        userInfoEntity.setOpenId(infoVo.getOpenId());
        String pwd = infoVo.getMobile();
        userInfoEntity.setWechat(infoVo.getWechat());
        if (infoVo.getMobile().length() > 6) {
            pwd = infoVo.getMobile().substring(infoVo.getMobile().length()-6,6);
        }
        String pwdString = SHA1.getDigestOfString(pwd);
        userInfoEntity.setPassword(pwdString);
        userInfoEntity.setCreator("wechatUser");
        userInfoEntity.setStatus(0);
        userInfoRepository.save(userInfoEntity);

        //保存预约信息到预约信息表
        TAppointmentInfoEntity appointmentEntity = new TAppointmentInfoEntity();
        appointmentEntity.setApponitmentClasses(infoVo.getApponitmentClasses());
        appointmentEntity.setApponitmentTime(infoVo.getApponitmentTime());
        appointmentEntity.setUserId(userInfoEntity.getUserId());
        appointmentEntity.setUserName(infoVo.getUserName());
        appointmentEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
        appointmentEntity.setMobile(infoVo.getMobile());
        appointmentEntity.setStatus(0);
        onLineBookClassesRepository.save(appointmentEntity);

        return appointmentEntity;
    }

    @Override
    public TAppointmentInfoEntity findAppointmentInfoByUserId(Integer userId) {
        TAppointmentInfoEntity appointmentInfo = onLineBookClassesRepository.findByUserId(userId);
        return appointmentInfo;
    }
}

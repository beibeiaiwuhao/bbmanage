package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.repository.UserInfoRepository;
import com.beibei.bbmanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年10月19日
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Page<TUserInfoEntity> findAllUser(Map<String, Object> queryCondition) {
        Integer limit = Integer.parseInt((String) queryCondition.get("limit"));
        Integer page = Integer.parseInt((String) queryCondition.get("page"));
//        String userName = (String) queryCondition.get("userName");
        Pageable pageable = new PageRequest(page,limit);
        Page<TUserInfoEntity> userNameLike = userInfoRepository.findAll( pageable);
        return userNameLike;
    }

    @Override
    public TUserInfoEntity findTUserInfoEntityByUserName(String userName) {


        return userInfoRepository.findTUserInfoEntityByUserName(userName);
    }
}

package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年10月19日
 */
public interface UserInfoService {

    public Page<TUserInfoEntity> findAllUser(Map<String, Object> queryContidion);

    public TUserInfoEntity findTUserInfoEntityByUserName(String userName);

    public TUserInfoEntity findTuserInfoEntityByOpenId(String openId);

    public ResponseEntity<Object> checkPhoneCode(String code,String mobile);


}

package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.redis.RedisBaseOperation;
import com.beibei.bbmanage.repository.UserInfoRepository;
import com.beibei.bbmanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired
    private RedisBaseOperation<Object> redisBaseOperation;

    @Override
    public Page<TUserInfoEntity> findAllUser(Map<String, Object> queryCondition) {
        Integer limit = Integer.parseInt((String) queryCondition.get("limit"));
        Integer page = Integer.parseInt((String) queryCondition.get("page"));
        Pageable pageable = new PageRequest(page,limit);
        Page<TUserInfoEntity> userNameLike = userInfoRepository.findAll( pageable);
        return userNameLike;
    }

    @Override
    public TUserInfoEntity findTUserInfoEntityByUserName(String userName) {

        return userInfoRepository.findTUserInfoEntityByUserName(userName);
    }

    @Override
    public TUserInfoEntity findTuserInfoEntityByOpenId(String openId) {
        return userInfoRepository.findTUserInfoEntityByOpenId(openId);
    }

    /**
     * 微信用户使用手机号登录
     * @param code
     * @param mobile
     * @return
     */
    @Override
    public ResponseEntity<Object> checkPhoneCode(String code, String mobile) {
        TUserInfoEntity userInfo = userInfoRepository.findTUserInfoEntityByMobile(mobile);
        if (userInfo == null) {//校验 用户是否存在
            return Response.serverError("用户不存在");
        }else  {//校验验证码是否正确
            String mobileCode = redisBaseOperation.getCode(mobile);
            if (!mobileCode.equals(code)) return Response.serverError("验证码输入错误");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",userInfo);
        return Response.success(map,"用户登录成功");
    }

}

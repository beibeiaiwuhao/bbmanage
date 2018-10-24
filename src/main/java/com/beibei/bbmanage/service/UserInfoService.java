package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年10月19日
 */
public interface UserInfoService {

    public Page<TUserInfoEntity> findAllUser(Map<String, Object> queryContidion);

}

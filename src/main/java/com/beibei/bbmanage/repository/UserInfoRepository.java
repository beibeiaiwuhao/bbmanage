package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户家长信息
 */
public interface UserInfoRepository extends CrudRepository<TUserInfoEntity,Integer> {


}

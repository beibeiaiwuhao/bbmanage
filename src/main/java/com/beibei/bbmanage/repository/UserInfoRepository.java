package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户家长信息
 */
public interface UserInfoRepository extends CrudRepository<TUserInfoEntity,Integer> {

    Page<TUserInfoEntity> findAllByUserNameLike(String userName, Pageable pageable);

    Page<TUserInfoEntity> findAll(Pageable pageable);

    TUserInfoEntity findTUserInfoEntityByUserName(String userName);

    TUserInfoEntity findTUserInfoEntityByOpenId(String openId);

}

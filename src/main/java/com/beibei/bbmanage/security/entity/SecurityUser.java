package com.beibei.bbmanage.security.entity;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class SecurityUser  extends TUserInfoEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(TUserInfoEntity userEntity) {
        if (userEntity != null) {
            this.setUserId(userEntity.getUserId());
            this.setUserName(userEntity.getUserName());
            this.setMobile(userEntity.getMobile());
            this.setPassword(userEntity.getPassword());
            this.setOldPassword(userEntity.getOldPassword());
            this.setSex(userEntity.getSex());
            this.setEmail(userEntity.getEmail());
            this.setQq(userEntity.getQq());
            this.setWechat(userEntity.getWechat());
            this.setAddress(userEntity.getAddress());
            this.setAvatarImgUrl(userEntity.getAvatarImgUrl());
            this.setStatus(userEntity.getStatus());
            this.setCreator(userEntity.getCreator());
            this.setCreateTime(userEntity.getCreateTime());
            this.setMender(userEntity.getMender());
            this.setMendTime(userEntity.getMendTime());
            this.setRemark(userEntity.getRemark());

        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return super.getPassword();

    }

    @Override
    public String getUsername() {

        return super.getUserName();

    }


    @Override
    public boolean isAccountNonExpired() {

        return true;

    }


    @Override
    public boolean isAccountNonLocked() {

        return true;

    }


    @Override
    public boolean isCredentialsNonExpired() {

        return true;

    }


    @Override
    public boolean isEnabled() {

        return true;

    }
}

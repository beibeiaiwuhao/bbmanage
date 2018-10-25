package com.beibei.bbmanage.security.service;

import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.security.entity.SecurityUser;
import com.beibei.bbmanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserInfoService userInfoService;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TUserInfoEntity entity = userInfoService.findTUserInfoEntityByUserName(userName);
        if (entity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!entity.getCreator().equals("admin")) {
            throw new UsernameNotFoundException("不是管理人员");
        }
        SecurityUser securityUser = new SecurityUser(entity);
        return securityUser;
    }
}

package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TSysMenuEntity;

import java.util.List;

/**
 * 首页菜单
 */
public interface SysMenuService {

    /**
     * 查询后台管理的菜单列表
     * @return
     */
    public List<TSysMenuEntity> findAllSysMenu();

}

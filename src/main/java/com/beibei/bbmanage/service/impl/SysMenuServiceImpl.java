package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TSysMenuEntity;
import com.beibei.bbmanage.repository.SysMenuRepository;
import com.beibei.bbmanage.service.SysMenuService;
import com.beibei.bbmanage.utils.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Override
    public List<TSysMenuEntity> findAllSysMenu() {
        return  IteratorUtils.copyIterator(sysMenuRepository.findAll().iterator());
    }
}

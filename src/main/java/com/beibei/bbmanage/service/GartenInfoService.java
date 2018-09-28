package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenInfoEntity;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 校园资讯板块
 */
public interface GartenInfoService {

    /**
     * 查询所有的校园资讯
     * @param queryCondition
     * @return
     */
    public Page<TGartenInfoEntity> findAllGarten(Map<String, Object> queryCondition );


    /**
     * 保存和修改园所信息
     * @param entity
     * @param images
     * @param imgUrl
     */
    public void saveNewGarden(TGartenInfoEntity entity, MultipartFile[] images, String imgUrl);

    /**
     * 根据幼儿园的状态查询幼儿园列表
     * @param status
     * @return
     */
    public List<TGartenInfoEntity> findAllByStatus(Integer status);



}

package com.beibei.bbmanage.service;


import com.beibei.bbmanage.entity.TIndexCategoryEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 首页板块分类
 */
public interface IndexCategoryService {

    /**
     * 根据id 查询 当没有id的时候查询全部列表
     * @param id
     * @return
     */
    public List<TIndexCategoryEntity> findAllIndexCategories(Integer id);

    /**
     * 保存首页板块
     * @param entity
     * @param images
     */
    public void saveIndexCategory(TIndexCategoryEntity entity, MultipartFile[] images);

    /**
     * 删除说选板块
     * @param id
     */
    public void deleteIndexCategory(Integer id);

}

package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TIndexBannerEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**˙
 * 轮播图
 */
public interface IndexBannerService {

    /**
     * 保存轮播图
     * @param entity
     * @param images
     * @param imgUrl
     */

    public void saveBanner(TIndexBannerEntity entity, MultipartFile[] images, String imgUrl);

    /**
     * 查询所有的轮播图
     * @return
     */
    public Page<TIndexBannerEntity> findAllBanner(Map<String, Object> queryCondition );


    /**
     * 查询所有的轮播图（小程序中使用）
     * @param status
     * @return
     */
    public List<TIndexBannerEntity> findAllBannerByStatus(Integer status);

    /**
     * 修改轮播图的使用状态
     * @param id
     * @param status
     */
    public void editRotationStatus(Integer id,Integer status);

    /**
     * 删除选中的轮播图
     * @param ids
     */
    public void deleteRotation(String[] ids);
}

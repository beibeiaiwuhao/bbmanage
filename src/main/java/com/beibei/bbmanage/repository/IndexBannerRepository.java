package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TIndexBannerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndexBannerRepository extends CrudRepository<TIndexBannerEntity,Integer> {

    /**
     * 修改商品的上架 下架 删除的状态
     * @param id
     * @param status
     */
    @Modifying(clearAutomatically = true)
    @Query("update TIndexBannerEntity t set "
            + " t.status =:status"
            + " where t.id =:id ")
    public void editRotationStatus(@Param("id") Integer id,
                               @Param("status") Integer status);

    /**
     * SHANCHU
     * @param ids
     */
    public void deleteByIdIn(@Param("ids")List<Integer> ids);

    /**
     * 简单分页查询
     * @param pageable
     * @return
     */
    Page<TIndexBannerEntity> findAll(Pageable pageable);

    /**
     * 搜索全部启用状态的轮播图
     * @param status
     * @return
     */
    List<TIndexBannerEntity> findAllByStatus(Integer status);


}

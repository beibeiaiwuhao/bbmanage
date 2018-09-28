package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenNoticeEntity;
import com.beibei.bbmanage.vo.GartenNoticeInfoVo;
import org.springframework.data.domain.Page;

/**
 * 园所通知公告
 */
public interface GartenNoticeService {

    /**
     * 保存园所的通知公告
     * @param entity
     */
    public void saveGardenNotice(TGartenNoticeEntity entity);

    /**
     * 根据搜索条件查询通知公告分页信息
     * @param minDate
     * @param maxDate
     * @param gardenId
     * @param page
     * @param size
     * @return
     */
    public Page<GartenNoticeInfoVo> findAllGardenNotice(String minDate, String maxDate, Integer gardenId, Integer page, Integer size);

}

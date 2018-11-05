package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenVideoEntity;
import com.beibei.bbmanage.vo.GartenVideoVo;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface GartenVideoService {

    /**
     * 保存上传的视频
     * @param entity
     * @param videoFile
     */
    public void saveGartenVideo(TGartenVideoEntity entity, MultipartFile[] videoFile);

    /**
     * 根据条件查询视频信息
     * @param page
     * @param size
     * @param minDate
     * @param maxDate
     * @param gartenId
     * @param classId
     * @return
     */
    public Page<GartenVideoVo> findGartenVideo(Integer page, Integer size, String minDate, String maxDate, Integer gartenId, Integer classId);


    /**
     *
     * @param gartenId
     * @return
     */
    public List<TGartenVideoEntity> findGartenVideoWithGartenId(Integer gartenId);

}

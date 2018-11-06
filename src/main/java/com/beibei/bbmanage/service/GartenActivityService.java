package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenActivityEntity;
import com.beibei.bbmanage.vo.ActivityPhotosFileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */
public interface GartenActivityService {

    Page<TGartenActivityEntity> findAll(Integer page,Integer size);

    void saveGartenActivity(TGartenActivityEntity entity);

    void saveActivityPhotos(MultipartFile[] imgFiles, Integer activityId);

    List<Map<String,Object>> findActivityPhotosByActivityId(Integer activityId);

}

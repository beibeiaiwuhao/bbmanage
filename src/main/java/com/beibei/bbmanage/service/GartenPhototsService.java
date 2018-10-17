package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenPhotosEntity;
import com.beibei.bbmanage.vo.GartenClassPhotosVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface GartenPhototsService {


    public void saveGartenPhoto(TGartenPhotosEntity entity, MultipartFile[] imgFiles);


    public List<GartenClassPhotosVo> getGartenClassPhotoInfo(Integer gartenId);


    public List<Map<String,Object>> getGartenClassPhotoInfoByClassId(Integer classId);

}

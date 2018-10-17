package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.entity.TGartenPhotosEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenPhototsService;
import com.beibei.bbmanage.vo.GartenClassPhotosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class GartenPhotosController {

    @Autowired
    private GartenPhototsService gartenPhototsService;

    @RequestMapping("/garten/photo/save")
    public ResponseEntity<Object> saveGartenPhoto(TGartenPhotosEntity entity, MultipartFile[] imgFile) {
        gartenPhototsService.saveGartenPhoto(entity,imgFile);
        return Response.success(null,"数据保存成功");
    }

    @RequestMapping("/getGartenClassPhotosCountByGartenId")
    public ResponseEntity<Object> getGartenClassPhotosCount(Integer gartenId) {
        List<GartenClassPhotosVo> photoInfo = gartenPhototsService.getGartenClassPhotoInfo(gartenId);
        return Response.success(photoInfo,"数据获取成功");
    }

    @RequestMapping("/getClassPhotosByClassId")
    public ResponseEntity<Object> getClassPhotosByClassId(Integer classId) {

        List<Map<String, Object>> info = gartenPhototsService.getGartenClassPhotoInfoByClassId(classId);

        return Response.success(info,"数据获取成功");
    }


}

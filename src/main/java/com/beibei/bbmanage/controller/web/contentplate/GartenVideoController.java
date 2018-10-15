package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.entity.TGartenVideoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenVideoService;
import com.beibei.bbmanage.vo.GartenVideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GartenVideoController {

    @Autowired
    private GartenVideoService gartenVideoService;

    @RequestMapping("/garten/video/save")
    public ResponseEntity<Object> saveGartenVideo(TGartenVideoEntity entity, MultipartFile[] videoFiles){
        gartenVideoService.saveGartenVideo(entity,videoFiles);
        return Response.success(null,"视频保存完成");
    }


    @RequestMapping("/garten/video/list")
    public ResponseEntity<Object> gartenVideoList(Integer page,Integer size,String minDate,String maxDate,Integer gartenId,Integer classId){
        Page<GartenVideoVo> gartenVideoVos = gartenVideoService.findGartenVideo(page, size, minDate, maxDate, gartenId, classId);
        return Response.success(gartenVideoVos,"数据获取成功");
    }









}

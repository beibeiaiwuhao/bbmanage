package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenPhototsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年11月06日
 */

@Controller
@RequestMapping("/api")
public class GartenPhotosAPIController {

    @Autowired
    private GartenPhototsService gartenPhototsService;

    @RequestMapping("/getPhotolistByGartenId")
    public ResponseEntity<Object> getPhotoListWithGartenId(Integer gartenId) {
        Map<String, Object> photoInfo = gartenPhototsService.findPhototsWithGartenId(gartenId);
        return Response.success(photoInfo,"相册信息获取成功");
    }



}

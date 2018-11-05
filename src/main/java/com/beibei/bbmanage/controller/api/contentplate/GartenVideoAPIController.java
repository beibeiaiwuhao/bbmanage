package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.entity.TGartenVideoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */
@Controller
@RequestMapping("/api")
public class GartenVideoAPIController {

    @Autowired
    private GartenVideoService gartenVideoService;


    @RequestMapping("/getGartenVideoListWithGartenId")
    public ResponseEntity<Object> getGartenVideoListWithGartenId(Integer gartenId) {
        Map<String,Object> map = new HashMap<>();
        List<TGartenVideoEntity> video = gartenVideoService.findGartenVideoWithGartenId(gartenId);
        map.put("videoInfo",video);
        return Response.success(map,"视频列表获取成功");
    }



}

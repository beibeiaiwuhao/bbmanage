package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.entity.TGartenInfoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年10月29日
 */

@Controller
@RequestMapping("/api")
public class GartenInfoAPIController {

    @Autowired
    private GartenInfoService gartenInfoService;

    @RequestMapping("/getGartenInfoByGartenId")
    public ResponseEntity<Object> getGartenInfoByGartenId(Integer gartenId) {
        TGartenInfoEntity gartenInfoEntity = gartenInfoService.findGartenInfoByGartenId(gartenId);
        Map<String,Object> map = new HashMap<>();
        map.put("gartenInfo",gartenInfoEntity);
        return Response.success(map,"幼儿园信息获取成功");
    }

}

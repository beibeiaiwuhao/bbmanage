package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.entity.TGartenTeacherEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenTeacherService;
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
 * date: 2018年11月02日
 */
@Controller
@RequestMapping("/api")
public class GartenTeacherAPIController {

    @Autowired
    private GartenTeacherService gartenTeacherService;

    @RequestMapping("/getTeacherListByGartenId")
    public ResponseEntity<Object> getTeacherListByGartenId(Integer gartenId) {
        Map<String,Object> map = new HashMap<>();
        List<TGartenTeacherEntity> list = gartenTeacherService.getTeacherListByGartenId(gartenId);
        map.put("teacherInfo",list);
        return Response.success(map,"老师列表获取成功");
    }


}

package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenCourseService;
import com.beibei.bbmanage.service.OnlineBookClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年10月31日
 */
@Controller
@RequestMapping("/api")
public class OnLineBookClassesAPIController {

    @Autowired
    private GartenCourseService gartenCourseService;


    @RequestMapping("/initOnlineBookClass")
    public ResponseEntity<Object> initOnlineBookClass() {
        Map<String,Object> map = new HashMap<>();
        map.put("courseInfo",gartenCourseService.getGartenCourseList());
        return Response.success(map,"数据获取成功");
    }







}

package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.entity.TGartenClassEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GartenClassesController {

    @Autowired
    private GartenClassesService gartenClassesService;

    @RequestMapping("/classes/getGartenClass")
    public ResponseEntity<Object> getGartenClass(Integer gartenId) {
        List<TGartenClassEntity> gartenClasses = gartenClassesService.getGartenClassesWithGartenId(gartenId);

        return Response.success(gartenClasses,"园所班级获取成功");
    }


}

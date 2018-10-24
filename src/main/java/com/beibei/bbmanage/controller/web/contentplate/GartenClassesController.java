package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TGartenClassEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GartenClassesController extends BaseController {

    @Autowired
    private GartenClassesService gartenClassesService;

    @RequestMapping("/classes/getGartenClass")
    public ResponseEntity<Object> getGartenClass(Integer gartenId) {
        List<TGartenClassEntity> gartenClasses = gartenClassesService.getGartenClassesWithGartenId(gartenId);

        return Response.success(gartenClasses,"园所班级获取成功");
    }

    @RequestMapping("/classes/page/classList")
    public ResponseEntity<Object> getPageClassList(HttpServletRequest request) {
        String[] tmp = new String[]{"gartenId"};
        Page<TGartenClassEntity> pageGartenClassWitQueryCondition = gartenClassesService.getPageGartenClassWitQueryCondition(super.getQueryCondition(request,tmp));
        return Response.success(pageGartenClassWitQueryCondition,"分页班级列表获取成功");

    }




    @RequestMapping("/classes/saveGartenClass")
    public ResponseEntity<Object> saveGartenClass(TGartenClassEntity entity) {
        gartenClassesService.saveGartenClasses(entity);

        return Response.success(null,"数据保存成功");
    }


}

package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenClassesService;
import com.beibei.bbmanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author haohao
 * <p>
 * date: 2018年11月23日
 */
@Controller
@RequestMapping("/api")
public class GartenClassesAPIController {

    @Autowired
    private GartenClassesService gartenClassesService;

    /**
     * 根据userId 查询用户的所在园所的班级
     * @param userId
     * @return
     */
    @RequestMapping("/getClassListByUserId")
    public ResponseEntity<Object> getClassListByUserId(Integer userId){



        return Response.success();
    }




}

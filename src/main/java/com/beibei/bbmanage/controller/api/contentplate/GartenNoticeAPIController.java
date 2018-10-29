package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author haohao
 * <p>
 * date: 2018年10月29日
 */

@Controller
@RequestMapping("/api")
public class GartenNoticeAPIController  {

    @Autowired
    private GartenNoticeService gartenNoticeService;

    @RequestMapping("/getGartenNoticeByGartenId")
    public ResponseEntity<Object> getGartenNoticeByGartenId(Integer gartenId) {

//            gartenNoticeService.
        return Response.success();
    }




}

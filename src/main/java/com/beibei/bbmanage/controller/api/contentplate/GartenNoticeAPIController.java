package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.entity.TGartenNoticeEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<TGartenNoticeEntity> noticeByGartenId = gartenNoticeService.findGartenNoticeByGartenId(gartenId);
        Map<String,Object> map = new HashMap<>();
        map.put("noticeInfo",noticeByGartenId);
        return Response.success(map,"通知公告数据获取成功");
    }





}

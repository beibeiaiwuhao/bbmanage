package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TGartenNoticeEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenNoticeService;
import com.beibei.bbmanage.vo.GartenNoticeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GartenNoticeController extends BaseController {

    @Autowired
    private GartenNoticeService gartenNoticeService;


    @RequestMapping("/garten/notice/save")
    public ResponseEntity<Object> saveGartenNotice(TGartenNoticeEntity entity) {
        gartenNoticeService.saveGardenNotice(entity);
        return Response.success(null,"通知公告保存成功");
    }

    @RequestMapping("/garten/notice/list")
    public ResponseEntity<Object> getGartenNoticeList(HttpServletRequest request) {

        Integer size = Integer.parseInt((String) request.getParameter("size"));
        Integer page = Integer.parseInt((String) request.getParameter("page"));
        Integer gartenId = Integer.parseInt((String) request.getParameter("gartenId"));

        Page<GartenNoticeInfoVo> gardenNotice = gartenNoticeService.findAllGardenNotice(
                request.getParameter("minDate"),
                request.getParameter("maxDate"),
                gartenId,
                page,
                size
        );
        return Response.success(gardenNotice,"获取通知公告列表成功");
    }


}

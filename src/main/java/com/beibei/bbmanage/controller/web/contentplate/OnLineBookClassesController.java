package com.beibei.bbmanage.controller.web.contentplate;


import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TAppointmentInfoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.OnlineBookClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OnLineBookClassesController extends BaseController {

    @Autowired
    private OnlineBookClassesService onlineBookClassesService;

    @RequestMapping("/online/book/list")
    public ResponseEntity<Object> getOnLineBookClassesList(HttpServletRequest request) {
//        String minDate,String maxDate,String userName,Integer page,Integer size
        Integer size = Integer.parseInt((String) request.getParameter("size"));
        Integer page = Integer.parseInt((String) request.getParameter("page"));

        Page<TAppointmentInfoEntity> pageResult = onlineBookClassesService.getOnlineBookClassesListWithDateAndUserName(
                request.getParameter("minDate"),
                request.getParameter("maxDate"),
                request.getParameter("userName"),
                page,
                size
        );
        return Response.success(pageResult,"预约课程列表获取成功");
    }



}

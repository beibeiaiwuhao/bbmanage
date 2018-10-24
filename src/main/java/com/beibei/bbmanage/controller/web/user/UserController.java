package com.beibei.bbmanage.controller.web.user;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haohao
 * <p>
 * date: 2018年10月19日
 */


@Controller
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/user/findAllUser")
    public ResponseEntity<Object> findAllUser(HttpServletRequest request) {
        String[] tmp = new String[]{"userName"};
        Page<TUserInfoEntity> user = userInfoService.findAllUser(super.getQueryCondition(request, tmp));
        return Response.success(user,"用户列表获取成功");
    }



}

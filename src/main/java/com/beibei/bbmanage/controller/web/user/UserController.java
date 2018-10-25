package com.beibei.bbmanage.controller.web.user;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.handler.GeetestConfig;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.UserInfoService;
import com.beibei.bbmanage.utils.GeetestLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author haohao
 * <p>
 * date: 2018年10月19日
 */


@Controller
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户列表
     * @param request
     * @return
     */
    @RequestMapping("/user/findAllUser")
    public ResponseEntity<Object> findAllUser(HttpServletRequest request) {
        String[] tmp = new String[]{"userName"};
        Page<TUserInfoEntity> user = userInfoService.findAllUser(super.getQueryCondition(request, tmp));
        return Response.success(user,"用户列表获取成功");
    }

    /**
     * 获取极验的验证码
     * @param response
     * @param request
     */
    @RequestMapping("/gt/register")
    public void start(HttpServletResponse response, HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());
        String resStr = "{}";
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        resStr = gtSdk.getResponseStr();
        try {
            PrintWriter out = response.getWriter();
            out.println(resStr);
        }catch (IOException e) {
        }
    }





}

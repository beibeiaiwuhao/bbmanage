package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.entity.TAppointmentInfoEntity;
import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenCourseService;
import com.beibei.bbmanage.service.OnlineBookClassesService;
import com.beibei.bbmanage.service.UserInfoService;
import com.beibei.bbmanage.utils.MobileMessageSend;
import com.beibei.bbmanage.vo.OnlineBookClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
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

    @Autowired
    private OnlineBookClassesService onlineBookClassesService;

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 初始化预约课程
     * @return
     */
    @RequestMapping("/initOnlineBookClass")
    public ResponseEntity<Object> initOnlineBookClass() {
        Map<String,Object> map = new HashMap<>();
        map.put("courseInfo",gartenCourseService.getGartenCourseList());
        return Response.success(map,"数据获取成功");
    }

    /**
     * 保存用户预约课程
     * @param onlineBookClassVo
     * @return
     */
    @RequestMapping("/saveOnlineBookClass")
    public ResponseEntity<Object> saveOnlineBookClass(OnlineBookClassVo onlineBookClassVo) {
        Map<String,Object> map = new HashMap<>();

        TAppointmentInfoEntity appointmentInfoEntity = onlineBookClassesService.saveOnlineClassInfo(onlineBookClassVo);
        map.put("status",1);
        map.put("appointmentInfo",appointmentInfoEntity);

        return Response.success(map,"数据保存成功");
    }

    /**
     * 查询用户之前是否已经预约
     *   如果已经预约，将预约信息查询并返回
     *   如果没有预约
     *   status 0 是该用户没有预约过 1 是该用户已经预约过
     * @param openId
     * @return
     */
    @RequestMapping("/checkUserHasBook")
    public ResponseEntity<Object> checkUserHasBook(String openId) {
        Map<String,Object> map = new HashMap<>();
        TUserInfoEntity userInfo = userInfoService.findTuserInfoEntityByOpenId(openId);
        if (userInfo == null) {
            map.put("status",0);
        }else {
            map.put("status",1);
            TAppointmentInfoEntity appointmentInfoByUserId = onlineBookClassesService.findAppointmentInfoByUserId(userInfo.getUserId());
            map.put("appointmentInfo",appointmentInfoByUserId);
        }
        return Response.success(map,"数据查询成功");
    }










}

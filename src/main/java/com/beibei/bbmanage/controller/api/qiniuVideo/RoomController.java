package com.beibei.bbmanage.controller.api.qiniuVideo;

import com.alibaba.fastjson.JSON;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.utils.DateUtil;
import com.qiniu.rtc.*;
import com.qiniu.rtc.model.RoomAccess;
import com.qiniu.util.Auth;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年11月18日
 */
@Controller
@RequestMapping("/api")
public class RoomController {

    private String APPID = "dto9qi1lp";
    private String ACCESS_KEY = "NaHP2d6pHgSUr-0nZ4B1cl4p6cPDaWluO0_s1F0D";
    private String SECRET_KEY = "nmv6Po7FMV7vtk7TxTSDn17HUSMlow4NHnmlqiCg";

    private String USER = "user";
    private String ADMIN = "admin";

    private Auth auth = null;
    {
        try {
            auth = Auth.create(ACCESS_KEY,SECRET_KEY);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    private RtcRoomManager rmanager = new RtcRoomManager(auth);

    /**
     *     public RoomAccess(String appId, String roomName, String userId, long expireAt, String permission) {
     * @param userName
     * @param roomName
     *
     * @return
     */
   @RequestMapping(value = "/getRoomToken",method = RequestMethod.POST)
    public ResponseEntity<Object> getRoomToken(String userName,String roomName) {

       String roomToken = "";
       try {
           roomToken = rmanager.getRoomToken(APPID, roomName, userName, getCurrentLongTime(), USER);
       } catch (Exception e) {
           e.printStackTrace();
       }
       Map<String,Object> map = new HashMap<>();
       map.put("roomToken",roomToken);
       return Response.success(map,"数据获取成功");
    }





    long getCurrentLongTime() {

        String format = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date  = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = (date.getTime()+60*60*1000)/1000;
        return time;
    }




}

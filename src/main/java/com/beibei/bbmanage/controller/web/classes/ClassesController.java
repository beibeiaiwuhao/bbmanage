package com.beibei.bbmanage.controller.web.classes;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.websocket.WHWebSocket;
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
 * date: 2018年10月18日
 */
@Controller
public class ClassesController {



    @Autowired
    private WHRunnable wHRunnable;

    @RequestMapping(value = "/test/easyWEbSocket")
    public ResponseEntity<Object> pushMessage(String msg) {
        Map<String,Object> map = new HashMap<>();
        Thread thread = new Thread(wHRunnable);
        thread.start();
        return Response.success(map,"访问成功");
    }

}

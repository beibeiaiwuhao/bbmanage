package com.beibei.bbmanage.controller.web.classes;


import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.websocket.WHWebSocket;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author haohao
 * <p>
 * date: 2018年10月18日
 */
@Component
public class WHRunnable implements Runnable {

    @Override
    public void run() {

        try {
            System.out.println(DateUtil.formatDateTime(new Date()));
            Thread.sleep(10000);
            WHWebSocket.sendInfo("吴昊发送的消息"+DateUtil.formatDateTime(new Date()));
            System.out.println(DateUtil.formatDateTime(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

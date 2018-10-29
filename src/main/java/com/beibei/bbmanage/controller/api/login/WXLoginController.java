package com.beibei.bbmanage.controller.api.login;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.redis.RedisBaseOperation;
import com.beibei.bbmanage.utils.Constants;
import com.beibei.bbmanage.utils.HttpClientUtils;
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
public class WXLoginController {

    @Autowired
    private RedisBaseOperation<Object> redisBaseOperation;

    @RequestMapping("/wx/login")
    public ResponseEntity<Object> getWXOpenId(String code) {
        String url = Constants.wxPages.WX_API_URl + "?appid=" + Constants.wxPages.WX_APP_ID + "&secret=" + Constants.wxPages.WX_APP_SECRET
                + "&js_code=" + code + "&graxnt_type=" +Constants.wxPages.WX_APP_GRANTTYPE;
        String data = HttpClientUtils.get(url);
        redisBaseOperation.saveOrUpdate(Constants.wxPages.WX_APP_LOGIN_DATA,data);
        return Response.success(data,"数据请求成功");
    }

}

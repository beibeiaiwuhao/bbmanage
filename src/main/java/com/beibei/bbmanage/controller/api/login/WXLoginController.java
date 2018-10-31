package com.beibei.bbmanage.controller.api.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.redis.RedisBaseOperation;
import com.beibei.bbmanage.utils.AesCbcUtil;
import com.beibei.bbmanage.utils.Constants;
import com.beibei.bbmanage.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 解密微信小程序用户信息
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    @RequestMapping(value = "/decodeUser",method = RequestMethod.POST)
    public ResponseEntity<Object> decodeUser(String encryptedData,String iv,String code) {

        Map<String,Object> map = new HashMap<>();
        if (code == null || code.length() == 0) {
            map.put("status",0);
            return Response.success(map,"code 不能为空");
        }
        //请求参数
        String params = "appid="+Constants.wxPages.WX_APP_ID+"&secret="+Constants.wxPages.WX_APP_SECRET+"&js_code="+code+"&grant_type="+Constants.wxPages.WX_APP_GRANTTYPE;
        //发送请求
        String sr = HttpClientUtils.get("https://api.weixin.qq.com/sns/jscode2session?"+params);

        JSONObject parse = (JSONObject) JSON.parse(sr);

        //获取会话密钥（session_key）
        String session_key = parse.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) parse.get("openid");

        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                JSONObject userInfoJSON = (JSONObject) JSON.parse(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
            }else {
                map.put("status", 0);
                map.put("msg", "解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.success(map,"返回数据成功");
    }


}

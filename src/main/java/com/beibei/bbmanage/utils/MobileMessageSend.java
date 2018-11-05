package com.beibei.bbmanage.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author haohao
 * <p>
 * date: 2018年11月01日
 */
public class MobileMessageSend {

    private static final String SERVER_URL="https://api.netease.im/sms/sendcode.action";//请求的URL
    private static final String APP_KEY="ecb939a3018d4eaaac307a513eb1c927";//账号
    private static final String APP_SECRET="137a3649c42a";//密码
    private static final String MOULD_ID="9394379";//模板ID
    private static final String NONCE="123456";

    public static int sendMsg(String phone) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(SERVER_URL);

        String curTime=String.valueOf((new Date().getTime()/1000L));
        String checkSum=CheckSumBuilder.getCheckSum(APP_SECRET,NONCE,curTime);

        //设置请求的header
        post.addHeader("AppKey",APP_KEY);
        post.addHeader("Nonce",NONCE);
        post.addHeader("CurTime",curTime);
        post.addHeader("CheckSum",checkSum);
        post.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //设置请求参数
        List<NameValuePair> nameValuePairs =new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("templateid",MOULD_ID));
        nameValuePairs.add(new BasicNameValuePair("mobile",phone));
//        nameValuePairs.add(new BasicNameValuePair("params","['"+msg+"']"));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));

        //执行请求
        HttpResponse response=httpclient.execute(post);
        String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");

        //判断是否发送成功，发送成功返回true
        String code= JSON.parseObject(responseEntity).getString("code");
        if (code.equals("200"))
        {return 0;}

        return 500;
    }

}

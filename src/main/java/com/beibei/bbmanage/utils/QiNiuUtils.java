package com.beibei.bbmanage.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;


public class QiNiuUtils {

    //密钥配置
    public static final Auth auth = Auth.create(Constants.qiNiuTypeKey.ACCESS_KEY,Constants.qiNiuTypeKey.SECRET_KEY);
    //创建上传对象
    public static final UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone0()));

    /**
     * 获取凭证
     *
     * @param bucketName 七牛云的数据库名称
     * @return
     */
    public String getUpToken(String bucketName) {
        return auth.uploadToken(bucketName);
    }


    /**
     * 上传
     *
     * @param file
     *           File对象
     * @param key
     *            上传到七牛上的文件的名称 （同一个空间下，名称【key】是唯一的）
     *
     */
    public String upload(File file, String key) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken(Constants.qiNiuTypeKey.BUCKET_NAME));
            //成功上传后返回图片url
            if (res.isOK()) {
                return Constants.qiNiuTypeKey.PICTURE_URL+key;
            }
            // 打印返回的信息
//            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                e.printStackTrace();
            }
        }
        return "" ;
    }

    /**
     * 删除
     *
     * @param files
     */
    public void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }


}

package com.beibei.bbmanage.utils;

public class Constants {

    //七牛配置所需要的
    public static class qiNiuTypeKey {
        public static final String ACCESS_KEY = "m2Zn29OXcy8nN_5yOXhsvDxE69StCWGaEBQk_oZV";
        public static final String SECRET_KEY = "68zxW_4cfbcyWAbuN9S9j09l_uJ0PH0DGMZwbciv";
        public static final String BUCKET_NAME = "hhcloud";
        public static final String PICTURE_URL = "http://pcvfcha51.bkt.clouddn.com/";
    }

    //Web路径地址
    public static class webPageName {
        public static final String MANAGER_INDEX = "common/index";//首页
        public static final String MANAGER_WELCOME = "common/welcome";//首页欢迎页
        public static final String MANAGER_PAGE_ROTATION = "management/homepage/page-rotation";//商品轮播图
        public static final String MANAGER_PAGE_ROTATION_ADD = "management/homepage/page-rotation-add";//商品轮播图添加
        public static final String MANAGER_PAGE_PANEL = "management/homepage/page-panel";//商品板块内容
        public static final String MANAGER_PAGE_PANEL_ADD = "management/homepage/page-panel-add";//商品板块内容添加
        public static final String MANAGER_PICTURE_SHOW = "common/picture-show";//图片展示页面
        public static final String MANAGER_CAMPUS_INFO = "management/contentplate/campus-info";//园所信息列表
        public static final String MANAGER_GARTEN_ADD = "management/contentplate/garten-add";//园所添加页面
        public static final String MANAGER_ONLINE_BOOKCLASS = "management/contentplate/online-bookClasses";//在线预约课程
        public static final String MANAGER_ANNOUNCEMENT = "management/contentplate/announcement";//通知公告
        public static final String MANAGER_ANNOUNCEMENT_ADD = "management/contentplate/announcement-add";//通知公告添加
        public static final String MANAGER_TEACHERS = "management/contentplate/teachers";//师生列表展示
        public static final String MANAGER_TEACHERS_ADD = "management/contentplate/teacher-add";//师生列表添加
        public static final String MANAGER_STUDENT_ADD = "management/contentplate/student-add";//师生列表添加
        public static final String MANAGER_FARM_RECIPES = "management/contentplate/farm-recipes";//园所食谱列表


    }

    //微信小程序配置所需要的
    public static class  wxPages{
        public static final String WX_API_URl = "https://api.weixin.qq.com/sns/jscode2session";//获取微信登陆的openId
        public static final String WX_APP_ID = "wxe04e81304960b5ef";
        public static final String WX_APP_SECRET = "7faa0bb9cb0c560f5b797dd884b8d5f7";
        public static final String WX_APP_GRANTTYPE = "authorization_code";
        public static final String WX_APP_LOGIN_DATA = "wx_login_data";
    }


    public final static String HTML_BR = "<br/>";

}

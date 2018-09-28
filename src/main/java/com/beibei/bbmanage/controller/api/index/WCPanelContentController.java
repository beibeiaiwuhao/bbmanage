package com.beibei.bbmanage.controller.api.index;

import com.beibei.bbmanage.entity.TIndexBannerEntity;
import com.beibei.bbmanage.entity.TIndexCategoryEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.IndexBannerService;
import com.beibei.bbmanage.service.IndexCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class WCPanelContentController {

    @Autowired
    private IndexBannerService indexBannerService;
    @Autowired
    private IndexCategoryService indexCategoryService;


    @RequestMapping("/index/panelContent")
    public ResponseEntity<Object> getIndexContent() {

        List<TIndexBannerEntity> bannerByStatus = indexBannerService.findAllBannerByStatus(0);

        List<TIndexCategoryEntity> indexCategories = indexCategoryService.findAllIndexCategories(null);

        Map<String,Object> map = new HashMap<>();
        map.put("panelContent",bannerByStatus.size()!= 0?bannerByStatus.get(0):null);
        //轮播图下面的分类模块
        map.put("categoryContent",indexCategories);
        return Response.success(map,"数据获取成功");
    }



}

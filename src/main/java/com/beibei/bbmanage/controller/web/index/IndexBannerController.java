package com.beibei.bbmanage.controller.web.index;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TIndexBannerEntity;

import com.beibei.bbmanage.handler.Response;

import com.beibei.bbmanage.service.IndexBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class IndexBannerController extends BaseController {

    @Autowired
    private IndexBannerService indexBannerService;

    /**
     * 保存商品
     * @param entity
     * @param images
     * @param imgUrl
     * @return
     */
    @RequestMapping("/panelcontent/save")
    public ResponseEntity<Object> saveIndexBanner(TIndexBannerEntity entity, MultipartFile[] images, String imgUrl) {
        indexBannerService.saveBanner(entity,images,imgUrl);
        return Response.success(null,"数据保存成功");
    }


    /**
     * 查询轮播图的列表
     * @return
     */
    @RequestMapping("/panelcontent/list")
    public ResponseEntity<Object> indexBannerList(HttpServletRequest request) {
        Map<String, Object> queryCondition = super.getQueryCondition(request, null);
        return Response.success( indexBannerService.findAllBanner(queryCondition),"轮播图列表获取成功");
    }


    /**
     * 修改轮播图的启用状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/page/rotation/editRotationStatus")
    public ResponseEntity<Object> editRotationStatus(Integer id,Integer status){
        indexBannerService.editRotationStatus(id,status);
        return Response.success();
    }


    @RequestMapping("/page/rotation/deleteRotation")
    public ResponseEntity<Object> deleteRotation(String ids) {

        indexBannerService.deleteRotation(ids.split(","));
        return Response.success();
    }



}

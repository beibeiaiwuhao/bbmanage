package com.beibei.bbmanage.controller.web.contentplate;



import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TGartenInfoEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class GartenInfoController extends BaseController {


    @Autowired
    private GartenInfoService gartenInfoService;

    /**
     * 查询校园信息列表
     * @param request
     * @return
     */
    @RequestMapping("/garten/list")
    public ResponseEntity<Object> findAllGarten(HttpServletRequest request) {
        Page<TGartenInfoEntity> infoEntities = gartenInfoService.findAllGarten(super.getQueryCondition(request, null));
        return Response.success(infoEntities,"校园资讯列表获取成功");
    }

    /**
     * 保存校园添加信息
     * @param entity
     * @param images
     * @param imgUrl
     * @return
     */
    @RequestMapping("/garden/save")
    public ResponseEntity<Object> saveNewgarden(TGartenInfoEntity entity, MultipartFile[] images, String imgUrl) {
        gartenInfoService.saveNewGarden(entity,images,imgUrl);
        return Response.success(null,"数据保存成功");
    }

    /**
     * 获取校园所有信息
     * @return
     */
    @RequestMapping("/garden/allInfo")
    public ResponseEntity<Object> findAllGardenInfoWithStatus() {
        return Response.success(gartenInfoService.findAllByStatus(0),"校园所有列表获取成功");
    }


}

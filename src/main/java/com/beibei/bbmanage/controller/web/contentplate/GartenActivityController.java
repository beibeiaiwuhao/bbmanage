package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TGartenActivityEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */

/**
 * 活动列表
 */
@Controller
public class GartenActivityController extends BaseController {

    @Autowired
    private GartenActivityService gartenActivityService;

    @RequestMapping("/activity/list")
    public ResponseEntity<Object> getActvityList(Integer page,Integer size) {

        Page<TGartenActivityEntity> activityList = gartenActivityService.findAll(page, size);

        return Response.success(activityList,"活动列表数据获取成功");
    }


    @RequestMapping("/activity/save")
    public ResponseEntity<Object> saveActivity(TGartenActivityEntity entity) {
        gartenActivityService.saveGartenActivity(entity);
        return Response.success(null,"活动保存成功");
    }

    @RequestMapping("/activity/photo/save")
    public ResponseEntity<Object> saveActivityPhotos(MultipartFile[] imgFile,Integer activityId) {
        gartenActivityService.saveActivityPhotos(imgFile,activityId);
        return Response.success(null,"活动照片保存成功");
    }

    @RequestMapping("/getActivityPhotoByActivityId")
    public ResponseEntity<Object> getActivityPhotoByActivityId(Integer activityId) {
        List<Map<String, Object>> photos = gartenActivityService.findActivityPhotosByActivityId(activityId);
        return Response.success(photos,"活动照片获取成功");
    }



}

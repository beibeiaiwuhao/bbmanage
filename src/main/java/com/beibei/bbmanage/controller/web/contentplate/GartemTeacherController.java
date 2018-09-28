package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TGartenInfoEntity;
import com.beibei.bbmanage.entity.TGartenTeacherEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenClassesService;
import com.beibei.bbmanage.service.GartenTeacherService;
import com.beibei.bbmanage.vo.GartenTeacherInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class GartemTeacherController extends BaseController {


    @Autowired
    private GartenTeacherService gartenTeacherService;

    /**
     * 获取添加幼儿园老师的初始化数据
     * @return
     */
    @RequestMapping("/teacher/initAddGartenTeacherData")
    public ResponseEntity<Object> initAddGartenTeacherData() {
        Map<String, Object> map = gartenTeacherService.initAddGartenTeacherData();
        return Response.success(map,"初始化数据获取成功");
    }

    /**
     * 保存新增老师信息
     * @param entity
     * @param images
     * @param imgUrl
     * @return
     */
    @RequestMapping("/teacher/save")
    public ResponseEntity<Object> saveNewgarden(GartenTeacherInfoVo entity, MultipartFile[] images, String imgUrl) {
        gartenTeacherService.saveNewGarden(entity,images,imgUrl);
        return Response.success(null,"数据保存成功");
    }


    /**
     * 获取筛选 的分页的教室列表信息
     * @param gartenId
     * @param classId
     * @param courseId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/teacher/list")
    public ResponseEntity<Object> showTeacherList(Integer gartenId, Integer classId, Integer courseId,Integer page,Integer size) {

        Page<GartenTeacherInfoVo> teacherInfoVos = gartenTeacherService.findTeacherWithconditions(gartenId, classId, courseId, page, size);

        return Response.success(teacherInfoVos,"教室列表获取成功");
    }


}

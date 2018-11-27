package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenStudentService;
import com.beibei.bbmanage.vo.GartenStudentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haohao
 * <p>
 * date: 2018年11月26日
 */

@Controller
@RequestMapping("/api")
public class GartenStudentAPIController {

    @Autowired
    private GartenStudentService gartenStudentService;


    @RequestMapping("/getStudentListByClassId")
    public ResponseEntity<Object> getStudentListByClassId(Integer classId) {
        List<GartenStudentInfoVo> student = gartenStudentService.findStudentWithClassId(classId);
        Map<String,Object> map = new HashMap<>();
        map.put("studentInfo",student);
        return Response.success(map,"获取学生列表成功");
    }

}

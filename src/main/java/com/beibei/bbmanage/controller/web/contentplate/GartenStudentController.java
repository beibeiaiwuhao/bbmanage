package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.utils.ExcelImportUtils;
import com.beibei.bbmanage.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Controller
public class GartenStudentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping("/import/student/info")
    public ResponseEntity<Object> importStudentInfoExcel(@RequestParam(value = "filename")MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            logger.info("解析excel文件不存在");
        }
        InputStream in = null;
        List<List<Object>> listOb = null;

        in = file.getInputStream();
        listOb = new ExcelImportUtils().getBankListByExcel(in,file.getOriginalFilename());
        in.close();
        for (int i = 0; i < listOb.size(); i ++) {
            List<Object> li = listOb.get(i);
            System.out.println("打印信息网站名称"+String.valueOf(li.get(0))+"网址"+String.valueOf(li.get(1))+"用户名"+String.valueOf(li.get(2))+"密码"+String.valueOf(li.get(3))+"日均访问量"+String.valueOf(li.get(4)) );
        }
        return Response.success(null,"学生信息导入成功");
    }



}

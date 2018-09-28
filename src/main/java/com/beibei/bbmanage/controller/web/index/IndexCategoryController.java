package com.beibei.bbmanage.controller.web.index;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.entity.TIndexCategoryEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.IndexCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexCategoryController extends BaseController {

    @Autowired
    private IndexCategoryService indexCategoryService;


    @RequestMapping("/page/panel/list")
    public ResponseEntity<Object> getPanelList(HttpServletRequest request) {
        String idP = request.getParameter("id");
        Integer id = 0;
        if (idP != null) {
            id = Integer.parseInt(idP);
        }
        return Response.success(indexCategoryService.findAllIndexCategories(id),"板块内容列表获取成功");
    }


    @RequestMapping("/page/panel/add")
    public ResponseEntity<Object> savePanel(TIndexCategoryEntity entity, MultipartFile[] images) {
        indexCategoryService.saveIndexCategory(entity,images);
        return Response.success(null,"内容保存成功");
    }


    @RequestMapping("/page/panel/deletePanel")
    public ResponseEntity<Object> deletePanel(Integer id) {
        indexCategoryService.deleteIndexCategory(id);
        return Response.success(null,"删除成功");
    }



}

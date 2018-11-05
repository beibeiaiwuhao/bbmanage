package com.beibei.bbmanage.controller.api.contentplate;

import com.beibei.bbmanage.entity.TGartenRecipeEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author haohao
 * <p>
 * date: 2018年11月02日
 */

@Controller
@RequestMapping("/api")
public class GartenRecipeAPIController {

    @Autowired
    private GartenRecipeService gartenRecipeService;


    /**
     * weekType = 0 是本周
     * weekType = 1 是下一周
     * weekType = -1 是上一周
     * @return
     */
    @RequestMapping("/getGartenRecipeByGartenId")
    public ResponseEntity<Object> getGartenRecipeByGartenId(Integer gartenId,Integer weekType) {
        List<TGartenRecipeEntity> gartenIdAndDate = gartenRecipeService.findAllRecipeByGartenIdAndDate(gartenId, weekType);
        Map<String,Object> map = new HashMap<>();
        map.put("recipeInfo",gartenIdAndDate);
        return Response.success(map,"食谱列表获取成功");
    }







}

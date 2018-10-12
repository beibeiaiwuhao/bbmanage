package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.entity.TGartenRecipeEntity;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenRecipeService;
import com.beibei.bbmanage.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class GartenRecipesController {

    @Autowired
    private GartenRecipeService gartenRecipeService;


    @RequestMapping("/farm/recipe/save")
    public ResponseEntity<Object> saveRecipe(TGartenRecipeEntity entity) {
        gartenRecipeService.saveRecipe(entity);
        return Response.success(null,"数据保存成功");
    }

    @RequestMapping("/farm/recipe/list")
    public ResponseEntity<Object> findAllFarmRecipes(Integer gartenId){
        List<TGartenRecipeEntity> recipes = gartenRecipeService.findAllRecipes(gartenId);
        return Response.success(recipes,"园所食谱数据获取成功");
    }

    @RequestMapping("/farm/recipe/mapList")
    @ResponseBody
    public List<Map<String,Object>> findRecipesById(Integer gartenId) {
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<TGartenRecipeEntity> recipes = gartenRecipeService.findAllRecipes(gartenId);
        for (TGartenRecipeEntity entity : recipes) {
            Map<String,Object> map = new HashMap<>();
            map.put("title",entity.getMealName()+entity.getMealDesc());
            map.put("allDay",false);
            map.put("start",entity.getRecipeDate());
            map.put("id",entity.getId());

            Date date = DateUtil.parseDateTime(entity.getRecipeDate());
            long time = date.getTime() + 30*60*1000;
            Date endDate = new Date(time);
            map.put("end",endDate);

            mapList.add(map);
        }
        return mapList;
    }



    @RequestMapping("/farm/findRecipeById")
    public ResponseEntity<Object> findRecipeById(Integer id) {
        TGartenRecipeEntity recipe = gartenRecipeService.findOneRecipe(id);
        return Response.success(recipe,"获取数据成功");
    }

    @RequestMapping("/farm/deleteRecipedById")
    public ResponseEntity<Object> deleteRecipeById(Integer id) {
        gartenRecipeService.deleteSlectedRecipe(id);
        return Response.success(null,"删除成功");
    }





}

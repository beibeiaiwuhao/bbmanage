package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.entity.TGartenRecipeEntity;

import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GartenRecipesController {

    @Autowired
    private GartenRecipeService gartenRecipeService;


    @RequestMapping("/farm/recipe/save")
    public ResponseEntity<Object> saveRecipe(TGartenRecipeEntity entity) {
        gartenRecipeService.saveRecipe(entity);

        return Response.success(null,"数据保存成功");
    }


}

package com.beibei.bbmanage.controller.web.sys;

import com.beibei.bbmanage.entity.TSysMenuEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.SysMenuService;
import com.beibei.bbmanage.vo.SysMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebSysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/index/menu")
    public ResponseEntity<Object> getSysMenu() {
        List<TSysMenuEntity> allSysMenu = sysMenuService.findAllSysMenu();
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",this.separateMenu(allSysMenu));
        return Response.success(map,"数据获取成功");
    }


    /**
     * 将菜单的一级和二级分别分开
     */

    private List<SysMenuVo> separateMenu(List<TSysMenuEntity> menus) {
        List<SysMenuVo> tmpArr1 = new ArrayList<>();
        for (TSysMenuEntity entity: menus) {
            SysMenuVo vo =  new SysMenuVo(entity);
            if (vo.getParentMenuCode().equals("0")){
                tmpArr1.add(vo);
            }
        }
        for (SysMenuVo tmpVo: tmpArr1) {
            List<SysMenuVo> tmpArr2 = new ArrayList<>();
            for (TSysMenuEntity entity: menus) {
                SysMenuVo vo1 =  new SysMenuVo(entity);
                if (tmpVo.getMenuCode().equals(vo1.getParentMenuCode())){
                    tmpArr2.add(vo1);
                }
            }
            tmpVo.setSecondMenuArr(tmpArr2);
        }
        return tmpArr1;
    }


}

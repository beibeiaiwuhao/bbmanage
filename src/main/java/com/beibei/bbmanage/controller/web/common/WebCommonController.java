package com.beibei.bbmanage.controller.web.common;

import com.beibei.bbmanage.entity.TSysMenuEntity;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.SysMenuService;
import com.beibei.bbmanage.utils.Constants;
import com.beibei.bbmanage.vo.SysMenuVo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebCommonController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        List<TSysMenuEntity> allSysMenu = sysMenuService.findAllSysMenu();
        model.addAttribute("menulist",this.separateMenu(allSysMenu));
        return Constants.webPageName.MANAGER_INDEX;
    }

    /**
     * 欢迎
     * @return
     */
    @RequestMapping("/welcome")
    public  String welcome() {
        return Constants.webPageName.MANAGER_WELCOME;
    }

    /**
     * 轮播图
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/management/page/rotation")
    public String pageRotation(HttpServletRequest request, Model model) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_PAGE_ROTATION;
    }

    /**
     * 轮播图添加
     * @return
     */
    @RequestMapping("/management/page/rotation/add")
    public String pageRotationAdd() {
        return Constants.webPageName.MANAGER_PAGE_ROTATION_ADD;
    }

    /**
     * 板块内容
     */
    @RequestMapping("/management/page/panel")
    public String pagePanel(HttpServletRequest request, Model model) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_PAGE_PANEL;
    }

    /**
     * 板块内容添加
     */
    @RequestMapping("/management/page/panel/add")
    public String pagePanelAdd(HttpServletRequest request, Model model) {
        return Constants.webPageName.MANAGER_PAGE_PANEL_ADD;
    }


    /**
     * 图片展示
     */
    @RequestMapping("/picture/show")
    public String pictureShow() {
        return Constants.webPageName.MANAGER_PICTURE_SHOW;
    }

    /**
     * 校园资讯列表
     */
    @RequestMapping("/management/contentplate/campus/info")
    public String campusInfo(Model model,HttpServletRequest request){
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_CAMPUS_INFO;
    }

    /**
     * 校园资讯添加
     */
    @RequestMapping("/management/contentplate/garten/add")
    public String gartenAdd(){
        return Constants.webPageName.MANAGER_GARTEN_ADD;
    }

    /**
     * 在线预约课程列表
     * @return
     */
    @RequestMapping("/management/contentplate/bookclasses/list")
    public String showBookClassesList(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_ONLINE_BOOKCLASS;
    }

    /**
     * 通知公告列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/management/contentplate/announcement/list")
    public String showAnnouncementList(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_ANNOUNCEMENT;
    }

    /**
     * 通知公告添加
     * @return
     */
    @RequestMapping("/management/contentplate/announcement/add")
    public String announcementAdd() {
        return Constants.webPageName.MANAGER_ANNOUNCEMENT_ADD;
    }


    /**
     * 师生列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/management/contentplate/announcement/teachers")
    public String showTeachersList(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_TEACHERS;
    }

    /**
     * 添加老师信息
     * @return
     */
    @RequestMapping("/management/contentplate/announcement/teacher/add")
    public String saveTeacher() {
        return Constants.webPageName.MANAGER_TEACHERS_ADD;
    }

    /**
     * 添加学生信息
     * @return
     */
    @RequestMapping("/management/contentplate/announcement/student/add")
    public String saveStuddent() {
        return Constants.webPageName.MANAGER_STUDENT_ADD;
    }

    /**
     * 导入学生信息
     * @return
     */
    @RequestMapping("/management/contentplate/announcement/student/import")
    public String importStuddent() {
        return Constants.webPageName.MANAGER_STUDENT_IMPORT;
    }

    /**
     * 园所食谱列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/management/contentplate/farm/recipes/list")
    public String showFarmRecipes(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return  Constants.webPageName.MANAGER_FARM_RECIPES;
    }

    /**
     * 添加园所食谱
     * @return
     */
    @RequestMapping("/management/contentplate/farm/recipes/add")
    public String saveFarmRecipes() {
        return Constants.webPageName.MANAGER_FARM_RECIPES_ADD;
    }

    /**
     * 宝贝视频展示
     * @return
     */
    @RequestMapping("/management/contentplate/baby/show")
    public String showBabyShowList(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_BABY_SHOW;
    }

    /**
     * 添加宝贝视频
     * @return
     */
    @RequestMapping("/management/contentplate/baby/show/add")
    public String addBabyShowList() {
        return Constants.webPageName.MANAGER_BABY_SHOW_ADD;
    }


    /**
     * 班级相册展示
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/management/contentplate/class/photo")
    public String showClassPhotots(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_CLASS_PHOTO;
    }

    /**
     * 添加班级相册
     * @return
     */
    @RequestMapping("/management/contentplate/class/photo/add")
    public String addClassPhotots() {
        return Constants.webPageName.MANAGER_CLASS_PHOTO_ADD;
    }


    /**
     * 班级相册分班显示
     * @return
     */
    @RequestMapping("/management/contentplate/class/photo/show")
    public String showClassPhotosList(){
        return Constants.webPageName.MANAGER_CLASS_PHOTO_SHOW;
    }


    /**
     * 每个班级相册的具体情况
     * @return
     */
    @RequestMapping("/management/contentplate/class/photoList/show")
    public String showClassPhotosDetailList() {
        return Constants.webPageName.MANAGER_CLASS_PHOTOLIST_SHOW;
    }


    /**
     * 班级列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/management/classes/classlist")
    public String showClassList(Model model,HttpServletRequest request) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_CLASS_LIST;
    }

    /**
     * 添加班级
     * @return
     */
    @RequestMapping("/management/classes/classadd")
    public String addClasses() {
        return Constants.webPageName.MANAGER_CLASS_ADD;
    }


    /**
     * 班级列表展示
     * @return
     */
    @RequestMapping("/management/classes/classListShow")
    public String classListShow(){
        return Constants.webPageName.MANAGER_CLASS_LIST_SHOW;
    }


    /**
     * 用户列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/management/userList")
    public String userList(HttpServletRequest request,Model model) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_USER_LIST;
    }

    /**
     *活动列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/management/contentplate/activity/list")
    public String activityList(HttpServletRequest request,Model model) {
        model.addAttribute("parentName",request.getParameter("parentName"));
        model.addAttribute("childName",request.getParameter("childName"));
        return Constants.webPageName.MANAGER_ACTIVITY_LIST;
    }

    /**
     * 新增活动
     * @return
     */
    @RequestMapping("/management/contentplate/activity/add")
    public String activityAdd() {
        return Constants.webPageName.MANAGER_ACTIVITY_ADD;
    }

    /**
     * 活动相册列表
     * @return
     */
    @RequestMapping("/management/contentplate/activity/photots")
    public String activityPhotoList() {
        return Constants.webPageName.MANAGER_ACTIVITY_PHOTOTS;
    }

    /**
     * 添加活动照片
     * @return
     */
    @RequestMapping("/management/contentplate/activity/photos/add")
    public String addActivityPhoto() {
        return Constants.webPageName.MANAGER_ACTIVITY_PHOTOTS_ADD;
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

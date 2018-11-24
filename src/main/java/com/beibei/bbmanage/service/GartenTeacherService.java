package com.beibei.bbmanage.service;

import com.beibei.bbmanage.entity.TGartenTeacherEntity;
import com.beibei.bbmanage.vo.GartenTeacherInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 园所老师
 */
public interface GartenTeacherService {

    /**
     * 初始化添加师生页面
     * @return
     */
    public Map<String,Object> initAddGartenTeacherData();

    /**
     * 保存新增的老师信息
     * @param entity
     * @param images
     * @param imgUrl
     */
    public void saveNewGarden(GartenTeacherInfoVo entity, MultipartFile[] images, String imgUrl);


    /**
     * 根据条件筛选 获得分页的老师列表
     * @param gartenId
     * @param classId
     * @param courseId
     * @return
     */
    Page<GartenTeacherInfoVo> findTeacherWithconditions(Integer gartenId, Integer classId,Integer courseId,Integer page,Integer size);

    /**
     * 根据classId 获取老师列表
     * @param classId
     * @return
     */
    List<TGartenTeacherEntity> getTeacherListByClassId(Integer classId);

    /**
     * 根据gartenId 获取老师列表
     * @param gartenId
     * @return
     */
    List<TGartenTeacherEntity> getTeacherListByGartenId(Integer gartenId);


    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    ResponseEntity<Object> getTeacherListByUserId(Integer userId);


}

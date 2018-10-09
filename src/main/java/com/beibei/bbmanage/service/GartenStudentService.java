package com.beibei.bbmanage.service;

import com.beibei.bbmanage.vo.GartenStudentFormVo;
import com.beibei.bbmanage.vo.GartenStudentInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * 园所学生
 */
public interface GartenStudentService {

    public String batchImport(String fileName, MultipartFile mfile);

    /**
     * 保存学生信息
     * @param studentVo
     * @param studentImage
     * @param parentImage
     */
    public void saveStudent(GartenStudentFormVo studentVo, MultipartFile[] studentImage, MultipartFile[] parentImage);

    /**
     * 根据条件筛选 获得分页的学生列表
     * @param gartenId
     * @param classId
     * @param courseId
     * @param page
     * @param size
     * @return
     */
    Page<GartenStudentInfoVo> findTeacherWithconditions(Integer gartenId, Integer classId, Integer courseId, Integer page, Integer size);


}

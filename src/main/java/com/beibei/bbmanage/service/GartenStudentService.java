package com.beibei.bbmanage.service;

import com.beibei.bbmanage.vo.GartenStudentInfoVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 园所学生
 */
public interface GartenStudentService {

    public String batchImport(String fileName, MultipartFile mfile);

    public void saveStudent(GartenStudentInfoVo studentVo,MultipartFile[] studentImage,MultipartFile[] parentImage);

}

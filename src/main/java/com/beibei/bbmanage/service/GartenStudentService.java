package com.beibei.bbmanage.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 园所学生
 */
public interface GartenStudentService {

    public String batchImport(String fileName, MultipartFile mfile);

}

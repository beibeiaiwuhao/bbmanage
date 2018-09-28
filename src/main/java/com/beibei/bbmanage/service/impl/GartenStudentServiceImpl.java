package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.service.GartenStudentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class GartenStudentServiceImpl implements GartenStudentService {


    @Override
    public String batchImport(String fileName, MultipartFile mfile) {
//        HSSFWorkbook wb = new HSSFWorkbook(mfile);


        return null;
    }
}

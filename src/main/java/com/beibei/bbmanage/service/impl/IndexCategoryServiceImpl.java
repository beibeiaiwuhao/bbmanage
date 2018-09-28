package com.beibei.bbmanage.service.impl;


import com.beibei.bbmanage.entity.TIndexCategoryEntity;
import com.beibei.bbmanage.repository.IndexCategoryRepository;
import com.beibei.bbmanage.service.IndexCategoryService;
import com.beibei.bbmanage.utils.*;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IndexCategoryServiceImpl implements IndexCategoryService {

    @Autowired
    private IndexCategoryRepository indexCategoryRepository;

    @Override
    public List<TIndexCategoryEntity> findAllIndexCategories(Integer id) {
        List<TIndexCategoryEntity> categoryEntities = new ArrayList<>();
        if (id == null || id == 0) {
            categoryEntities = IteratorUtils.copyIterator(indexCategoryRepository.findAll().iterator());
        }else {
            TIndexCategoryEntity tIndexCategoryEntity  = indexCategoryRepository.findOne(id);
            categoryEntities.add(tIndexCategoryEntity);
        }
        return categoryEntities;
    }

    @Override
    @Transactional
    public void saveIndexCategory(TIndexCategoryEntity entity, MultipartFile[] images) {
        QiNiuUtils qiNiuUtils = new QiNiuUtils();
        List<String> imgNames = new ArrayList<>();
        for (MultipartFile file : images) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件后缀
            String prefix=fileName.substring(fileName.lastIndexOf("."));
            File excelFile = null;
            try {
                excelFile = File.createTempFile(IDUtils.genImageName(),prefix);
                file.transferTo(excelFile);
            }catch (Exception e) {
                e.printStackTrace();
            }
            if (excelFile != null) {
                String uploadUrl = qiNiuUtils.upload(excelFile, IDUtils.genImageName());
                imgNames.add(uploadUrl);
            }
            //程序结束时，删除临时文件
            qiNiuUtils.deleteFile(excelFile);
        }
        String image = StringUtils.join(imgNames,",");
        //补全所有属性
        String currentTime = DateUtil.formatDateTime(new Date());
        entity.setCreateTime(currentTime);
        entity.setMendTime(currentTime);
        entity.setPictureUrl(image);
        List<String> allPageCode = indexCategoryRepository.findAllPageCode();
        entity.setPageCode(this.getPageCode(allPageCode));

        indexCategoryRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteIndexCategory(Integer id) {
        indexCategoryRepository.delete(id);
    }


    /**
     * 生成page_code的方法
     */

    private String getPageCode(List<String> allPageCode) {
        Integer currentValue = 1;
        for (String currentMaxCode: allPageCode) {
            List<String> tmpArr = Arrays.asList(currentMaxCode.split("H"));
            if (currentValue <= Integer.parseInt(tmpArr.get(1))) {
                currentValue = Integer.parseInt(tmpArr.get(1))+1;
            }
        }
        if (currentValue < 10) {
            return  "WH00"+currentValue;
        } else if (currentValue >= 100) {
            return  "WH0"+currentValue;
        } else{
            return  "WH"+currentValue;
        }
    }



}

package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TGartenInfoEntity;
import com.beibei.bbmanage.entity.TIndexBannerEntity;
import com.beibei.bbmanage.repository.GartenInfoRepository;
import com.beibei.bbmanage.service.GartenInfoService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.IDUtils;
import com.beibei.bbmanage.utils.QiNiuUtils;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class GartenInfoServiceImpl implements GartenInfoService {

    @Autowired
    private GartenInfoRepository gartenInfoRepository;


    @Override
    public Page<TGartenInfoEntity> findAllGarten(Map<String, Object> queryCondition) {
        Integer limit = Integer.parseInt((String) queryCondition.get("limit"));
        Integer page = Integer.parseInt((String) queryCondition.get("page"));
        Pageable pageable = new PageRequest(page,limit);
        Page<TGartenInfoEntity> infoEntities = gartenInfoRepository.findAll(pageable);
        return infoEntities;
    }

    @Override
    public void saveNewGarden(TGartenInfoEntity entity, MultipartFile[] images, String imgUrl) {
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
        if (imgUrl != null && !"".equals(imgUrl)) {
            if (image != null && !"".equals(image)) {
                image = image + "," +imgUrl;
            }else {
                image = imgUrl;
            }

        }
        //补全所有属性
        String currentTime = DateUtil.formatDateTime(new Date());
        entity.setCreateTime(currentTime);
        entity.setMendTime(currentTime);
        entity.setPictureUrl(image);
        //0是可用 1是不可用
//        entity.setStatus(0);
        gartenInfoRepository.save(entity);
    }

    @Override
    public List<TGartenInfoEntity> findAllByStatus(Integer status) {
        return gartenInfoRepository.findAllByStatus(status);
    }
}

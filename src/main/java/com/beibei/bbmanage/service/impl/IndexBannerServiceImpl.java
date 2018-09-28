package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.entity.TIndexBannerEntity;
import com.beibei.bbmanage.repository.IndexBannerRepository;
import com.beibei.bbmanage.service.IndexBannerService;
import com.beibei.bbmanage.utils.*;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class IndexBannerServiceImpl implements IndexBannerService {

    @Autowired
    private IndexBannerRepository indexBannerRepository;

    @Override
    @Transactional
    public void saveBanner(TIndexBannerEntity entity, MultipartFile[] images, String imgUrl) {
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
        entity.setImageUrl(image);
        //0是可用 1是不可用
//        entity.setStatus(0);
        indexBannerRepository.save(entity);
    }

    @Override
    public Page<TIndexBannerEntity> findAllBanner(Map<String, Object> queryCondition ) {
        Integer limit = Integer.parseInt((String) queryCondition.get("limit"));
        Integer page = Integer.parseInt((String) queryCondition.get("page"));
        Pageable pageable = new PageRequest(page,limit);
        Page<TIndexBannerEntity> bannerEntities = indexBannerRepository.findAll(pageable);
        return bannerEntities;
    }

    @Override
    public List<TIndexBannerEntity> findAllBannerByStatus(Integer status) {
        return indexBannerRepository.findAllByStatus(status);
    }

    @Override
    @Transactional
    public void editRotationStatus(Integer id, Integer status) {
        indexBannerRepository.editRotationStatus(id,status);
    }

    @Override
    @Transactional
    public void deleteRotation(String[] ids) {
        List<Integer> idArr = new ArrayList<>();
        for (String id :
                ids) {
            idArr.add(((new Integer(id)).intValue()));
        }
        indexBannerRepository.deleteByIdIn(idArr);
    }
}

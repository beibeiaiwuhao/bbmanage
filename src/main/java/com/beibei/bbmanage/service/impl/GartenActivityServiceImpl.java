package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.ActivityPhotosDao;
import com.beibei.bbmanage.entity.TActivityFileEntity;
import com.beibei.bbmanage.entity.TActivityPhotosEntity;
import com.beibei.bbmanage.entity.TGartenActivityEntity;
import com.beibei.bbmanage.repository.ActivityFileRepository;
import com.beibei.bbmanage.repository.ActivityPhotosRepository;
import com.beibei.bbmanage.repository.GartenActivityRepository;
import com.beibei.bbmanage.service.GartenActivityService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.IDUtils;
import com.beibei.bbmanage.utils.QiNiuUtils;
import com.beibei.bbmanage.vo.ActivityPhotosFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

/**
 * @author haohao
 * <p>
 * date: 2018年11月05日
 */

@Service
public class GartenActivityServiceImpl implements GartenActivityService {

    @Autowired
    private GartenActivityRepository gartenActivityRepository;

    @Autowired
    private ActivityFileRepository activityFileRepository;

    @Autowired
    private ActivityPhotosRepository activityPhotosRepository;

    @Autowired
    private DaoUtil daoUtil;


    @Override
    public Page<TGartenActivityEntity> findAll(Integer page,Integer size) {
        Pageable pageable = new PageRequest(page,size);
        return gartenActivityRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void saveGartenActivity(TGartenActivityEntity entity) {
        gartenActivityRepository.save(entity);
    }

    @Override
    @Transactional
    public void saveActivityPhotos(MultipartFile[] imgFiles, Integer activityId) {
        QiNiuUtils qiNiuUtils = new QiNiuUtils();
        String currentTime = DateUtil.formatDateTime(new Date());
        List<TActivityFileEntity> fileList = new ArrayList<>();

        for (MultipartFile file : imgFiles) {

            TActivityFileEntity entity = new TActivityFileEntity();

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
                String uploadUrl = qiNiuUtils.upload(excelFile, IDUtils.genImageName()+prefix);
                entity.setFileName(file.getOriginalFilename());
                entity.setFileType("1");
                entity.setFileUrl(uploadUrl);
                entity.setFileSize((double) (excelFile.length()/1000));
                entity.setStatus(0);
                entity.setCreateTime(currentTime);
                fileList.add(entity);
            }
            //程序结束时，删除临时文件
            qiNiuUtils.deleteFile(excelFile);
        }

        for  (TActivityFileEntity fileEntity: fileList) {
            activityFileRepository.save(fileEntity);
            TActivityPhotosEntity photosEntity = new TActivityPhotosEntity();
            photosEntity.setActivityId(activityId);
            photosEntity.setPhotoId(fileEntity.getId());
            activityPhotosRepository.save(photosEntity);
        }
    }

    @Override
    public List<Map<String,Object>> findActivityPhotosByActivityId(Integer activityId) {
        String sql = ActivityPhotosDao.getActivityPhotos(activityId);
        List<ActivityPhotosFileVo> photosList = daoUtil.getResultList(sql, ActivityPhotosFileVo.class);

        List<String> timeArr = new ArrayList<>();
        List<Map<String,Object>> resultList = new ArrayList<>();
        for ( ActivityPhotosFileVo fileVo: photosList) {
            String tmpDate = DateUtil.switchCrteaTime(fileVo.getCreateTime(), "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日");
            if (!timeArr.contains(tmpDate)) {
                timeArr.add(tmpDate);
            }
        }

        for (String time: timeArr) {
            Map<String,Object> map = new HashMap<>();
            map.put("time",time);
            List<ActivityPhotosFileVo> tmpList = new ArrayList<>();
            for ( ActivityPhotosFileVo fileVo: photosList) {
                String tmpDate = DateUtil.switchCrteaTime(fileVo.getCreateTime(), "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日");
                if (tmpDate.equals(time)) {
                    tmpList.add(fileVo);
                }
            }
            map.put("photoList",tmpList);
            resultList.add(map);
        }

        return resultList;
    }
}

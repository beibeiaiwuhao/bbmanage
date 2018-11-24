package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.ActivityPhotosDao;
import com.beibei.bbmanage.customsql.contentplate.GartenPhotosDao;
import com.beibei.bbmanage.entity.TGartenPhotosEntity;
import com.beibei.bbmanage.repository.GartenPhototsRepository;
import com.beibei.bbmanage.service.GartenPhototsService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.IDUtils;
import com.beibei.bbmanage.utils.QiNiuUtils;
import com.beibei.bbmanage.vo.ActivityPhotosFileVo;
import com.beibei.bbmanage.vo.GartenClassPhotosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GartenPhototsServiceImpl implements GartenPhototsService {

    @Autowired
    private GartenPhototsRepository gartenPhototsRepository;

    @Autowired
    private DaoUtil daoUtil;

    @Override
    @Transactional
    public void saveGartenPhoto(TGartenPhotosEntity entity, MultipartFile[] imgFiles) {
        QiNiuUtils qiNiuUtils = new QiNiuUtils();
        List<String> imgNames = new ArrayList<>();
        List<String> imgTitle = new ArrayList<>();
        List<String> imgSize = new ArrayList<>();

        for (MultipartFile file : imgFiles) {
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
                imgNames.add(uploadUrl);
                imgTitle.add(fileName);
                imgSize.add(String.valueOf(excelFile.length()/1000));
            }
            //程序结束时，删除临时文件
            qiNiuUtils.deleteFile(excelFile);
        }
        String currentTime = DateUtil.formatDateTime(new Date());
        for  (String imgUrl: imgNames) {
            TGartenPhotosEntity uploadEntity = new TGartenPhotosEntity();
            uploadEntity.setGartenId(entity.getGartenId());
            uploadEntity.setClassId(entity.getClassId());
            uploadEntity.setCreator(entity.getCreator());
            uploadEntity.setCreateTime(currentTime);
            uploadEntity.setPhotoUrl(imgUrl);
            uploadEntity.setPhotoName(imgTitle.get(imgNames.indexOf(imgUrl)));
            uploadEntity.setPhotoSize(imgSize.get(imgNames.indexOf(imgUrl)));
            gartenPhototsRepository.save(uploadEntity);
        }
    }

    /**
     *
     * @param gartenId
     * @return
     */
    @Override
    public List<GartenClassPhotosVo> getGartenClassPhotoInfo(Integer gartenId) {
        String sql = GartenPhotosDao.getGartenPhotosClass(gartenId);
        List<GartenClassPhotosVo> resultList = daoUtil.getResultList(sql, GartenClassPhotosVo.class);
        return resultList;
    }

    /**
     *
     * @param classId
     * @return
     */
    @Override
    public List<Map<String,Object>> getGartenClassPhotoInfoByClassId(Integer classId) {
        List<TGartenPhotosEntity> entitiesByClassId = gartenPhototsRepository.findTGartenPhotosEntitiesByClassId(classId);
        List<String> timeArr = new ArrayList<>();
        List<Map<String,Object>> resultList = new ArrayList<>();
        for ( TGartenPhotosEntity entity: entitiesByClassId) {
            String tmpTime = DateUtil.switchCrteaTime(entity.getCreateTime(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
            if (!timeArr.contains(tmpTime)) {
                timeArr.add(tmpTime);
            }
        }
        for (String time: timeArr) {
            Map<String,Object> map = new HashMap<>();
            map.put("time",time);
            List<TGartenPhotosEntity> tmpList = new ArrayList<>();
            for ( TGartenPhotosEntity entity: entitiesByClassId) {
                String tmpTime = DateUtil.switchCrteaTime(entity.getCreateTime(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
                if (tmpTime.equals(time)) {
                    tmpList.add(entity);
                }
            }
            map.put("photoList",tmpList);
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public Map<String, Object> findPhototsWithGartenId(Integer gartenId) {

        Map<String,Object> map = new HashMap<>();

        //查询园所下所有的照片
        int i = gartenPhototsRepository.countAllByGartenId(gartenId);

        map.put("gartenImgCount",i);
        map.put("currentDate",DateUtil.format(new Date(),"yyyy年MM月dd日"));

        //先查询最新照片（）
        List<TGartenPhotosEntity> entitiesByCreateTime = gartenPhototsRepository.findTGartenPhotosEntitiesByCreateTimeLike(DateUtil.format(new Date(), "yyyy-MM-dd")+"%");
        map.put("lastUpdateImg",entitiesByCreateTime);

        //查询有该园所下有多少活动，每个活动下有多少照片
        String sql = ActivityPhotosDao.getWXActivityIndex(gartenId);
        List<ActivityPhotosFileVo> activityList = daoUtil.getResultList(sql, ActivityPhotosFileVo.class);
        map.put("activityList",activityList);
        return map;
    }




}

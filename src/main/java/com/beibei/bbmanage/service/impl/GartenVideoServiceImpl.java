package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.GartenVideoDao;
import com.beibei.bbmanage.entity.TGartenVideoEntity;
import com.beibei.bbmanage.repository.GartenVideoRepository;
import com.beibei.bbmanage.service.GartenVideoService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.IDUtils;
import com.beibei.bbmanage.utils.QiNiuUtils;
import com.beibei.bbmanage.vo.GartenVideoVo;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GartenVideoServiceImpl implements GartenVideoService {

    @Autowired
    private GartenVideoRepository gartenVideoRepository;

    @Autowired
    private DaoUtil daoUtil;

    @Override
    @Transactional
    public void saveGartenVideo(TGartenVideoEntity entity, MultipartFile[] videoFile) {

        QiNiuUtils qiNiuUtils = new QiNiuUtils();
        List<String> videoNames = new ArrayList<>();
        //上传视频文件
        if (videoFile != null) {
            for (MultipartFile file : videoFile) {
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
                    videoNames.add(uploadUrl);
                }
                //程序结束时，删除临时文件
                qiNiuUtils.deleteFile(excelFile);
            }
        }

        String currentTime = DateUtil.formatDateTime(new Date());
        entity.setCreateTime(currentTime);
        entity.setStatus(0);
        entity.setVideoUrl(StringUtils.join(videoNames,","));
        gartenVideoRepository.save(entity);

    }

    @Override
    public Page<GartenVideoVo> findGartenVideo(Integer page, Integer size, String minDate, String maxDate, Integer gartenId, Integer classId) {
        String sql = GartenVideoDao.getGartenVideoList(minDate, maxDate, gartenId, classId);
        Page<GartenVideoVo> resultList = daoUtil.getPagerResultList(sql, page, size, GartenVideoVo.class);
        return resultList;
    }
    @Override
    public List<TGartenVideoEntity> findGartenVideoWithGartenId(Integer gartenId) {
        return gartenVideoRepository.findTGartenVideoEntitiesByGartenIdOrderByCreateTimeDesc(gartenId);
    }

}

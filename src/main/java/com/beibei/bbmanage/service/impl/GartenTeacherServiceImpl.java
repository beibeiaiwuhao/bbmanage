package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.GartenTeacherDao;
import com.beibei.bbmanage.entity.TClassTeacherEntity;
import com.beibei.bbmanage.entity.TGartenCourseEntity;
import com.beibei.bbmanage.entity.TGartenInfoEntity;
import com.beibei.bbmanage.entity.TGartenTeacherEntity;
import com.beibei.bbmanage.repository.ClassTeacherRepository;
import com.beibei.bbmanage.repository.GartenCourseRepository;
import com.beibei.bbmanage.repository.GartenInfoRepository;
import com.beibei.bbmanage.repository.GartenTeacherRepository;
import com.beibei.bbmanage.service.GartenTeacherService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.IDUtils;
import com.beibei.bbmanage.utils.IteratorUtils;
import com.beibei.bbmanage.utils.QiNiuUtils;
import com.beibei.bbmanage.vo.GartenTeacherInfoVo;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

@Service
public class GartenTeacherServiceImpl implements GartenTeacherService {

    @Autowired
    private GartenInfoRepository gartenInfoRepository;

    @Autowired
    private GartenCourseRepository gartenCourseRepository;

    @Autowired
    private GartenTeacherRepository gartenTeacherRepository;

    @Autowired
    private ClassTeacherRepository classTeacherrepository;

    @Autowired
    private DaoUtil daoUtil;




    /**
     * 查询所有的幼儿园信息和课程信息
     * @return
     */
    @Override
    public Map<String, Object> initAddGartenTeacherData() {
        //所有营业状态的幼儿园
        List<TGartenInfoEntity> gartenInfoEntities = gartenInfoRepository.findAllByStatus(0);
        //课程
        List<TGartenCourseEntity> courseEntities = IteratorUtils.copyIterator(gartenCourseRepository.findAll().iterator());
        Map<String,Object> map = new HashMap<>();
        map.put("gartenInfo",gartenInfoEntities);
        map.put("courseInfo",courseEntities);
        return map;
    }

    @Override
    @Transactional
    public void saveNewGarden(GartenTeacherInfoVo teacherVo, MultipartFile[] images, String imgUrl) {
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

        TGartenTeacherEntity teacherEntity = new TGartenTeacherEntity();
        teacherEntity.setAvatarImgUrl(image);
        teacherEntity.setGartenId(teacherVo.getGartenId());
        teacherEntity.setTeacherName(teacherVo.getTeacherName());
        teacherEntity.setTeacherMobile(teacherVo.getTeacherMobile());
        teacherEntity.setTeacherDesc(teacherVo.getTeacherDesc());
        teacherEntity.setTeacherClasses(teacherVo.getTeacherClasses());
        teacherEntity.setWechat(teacherVo.getWechat());

        //0:在职 1:离职
        teacherEntity.setStatus(0);
        teacherEntity.setCourseId(teacherVo.getCourseId());
        teacherEntity.setGender(teacherVo.getGender());
        gartenTeacherRepository.save(teacherEntity);
        TClassTeacherEntity classTeacher = new TClassTeacherEntity();
        classTeacher.setClassId(teacherVo.getClassId());
        classTeacher.setTeacherId(teacherEntity.getId());
        classTeacherrepository.save(classTeacher);



    }

    @Override
    public Page<GartenTeacherInfoVo> findTeacherWithconditions(Integer gartenId, Integer classId, Integer courseId,Integer page,Integer size) {
        String sql = GartenTeacherDao.getGartenTeacherListWithGartenIdAndClassIdAndCourseId(gartenId, classId, courseId);
        Page<GartenTeacherInfoVo> resultList = daoUtil.getPagerResultList(sql, page, size, GartenTeacherInfoVo.class);
        return resultList;
    }

    @Override
    public List<TGartenTeacherEntity> getTeacherListByClassId(Integer classId) {
        String sql = GartenTeacherDao.getGartenTeacherListWithGartenIdAndClassIdAndCourseId(null, classId, null);
        List<TGartenTeacherEntity> resultList = daoUtil.getResultList(sql, TGartenTeacherEntity.class);
        return resultList;
    }


}

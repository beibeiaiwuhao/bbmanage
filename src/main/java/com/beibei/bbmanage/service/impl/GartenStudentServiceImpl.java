package com.beibei.bbmanage.service.impl;

import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.GartenStudentDao;
import com.beibei.bbmanage.entity.TClassStudentEntity;
import com.beibei.bbmanage.entity.TGartenStudentEntity;
import com.beibei.bbmanage.entity.TUserInfoEntity;
import com.beibei.bbmanage.entity.TUserStudentEntity;
import com.beibei.bbmanage.repository.ClassStudentRepository;
import com.beibei.bbmanage.repository.GartenStudentRepository;
import com.beibei.bbmanage.repository.UserInfoRepository;
import com.beibei.bbmanage.repository.UserStudentRepository;
import com.beibei.bbmanage.service.GartenStudentService;
import com.beibei.bbmanage.utils.IDUtils;
import com.beibei.bbmanage.utils.QiNiuUtils;
import com.beibei.bbmanage.utils.SHA1;
import com.beibei.bbmanage.vo.GartenStudentFormVo;
import com.beibei.bbmanage.vo.GartenStudentInfoVo;
import com.beibei.bbmanage.vo.GartenTeacherInfoVo;
import com.qiniu.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class GartenStudentServiceImpl implements GartenStudentService {

    @Autowired
    private GartenStudentRepository gartenStudentRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserStudentRepository userStudentRepository;

    @Autowired
    private ClassStudentRepository classStudentRepository;

    @Autowired
    private DaoUtil daoUtil;


    @Override
    public String batchImport(String fileName, MultipartFile mfile) {
//      HSSFWorkbook wb = new HSSFWorkbook(mfile);
        return null;
    }

    @Override
    @Transactional
    public void saveStudent(GartenStudentFormVo studentVo, MultipartFile[] studentImage, MultipartFile[] parentImage) {
        QiNiuUtils qiNiuUtils = new QiNiuUtils();
        List<String> imgNames = new ArrayList<>();
        //上传学生头像
        if (studentImage != null) {
            for (MultipartFile file : studentImage) {
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
        }


        TGartenStudentEntity studentEntity = new TGartenStudentEntity();
        studentEntity.setStudentName(studentVo.getStudentName());
        studentEntity.setGartenId(studentVo.getGartenId());
        studentEntity.setStudentDesc(studentVo.getStudentHobbies());
        studentEntity.setStudentAge(studentVo.getStudentAge());
        studentEntity.setCourseId(studentVo.getCourseId());
        studentEntity.setStatus(0);
        studentEntity.setCourseName(studentVo.getCourseName());
        studentEntity.setAvatarImgUrl(StringUtils.join(imgNames,","));
        studentEntity.setStudentGender(studentVo.getStudentGender());
        //存储学生信息
        gartenStudentRepository.save(studentEntity);

        TUserStudentEntity userStudentEntity = new TUserStudentEntity();
        //当家长信息不存在的时候，存储家长信息
        if (studentVo.getIsExist().equals("1")) {
            List<String> parentImg = new ArrayList<>();
            if (parentImage != null ){
                //上传家长头像
                for (MultipartFile file : parentImage) {
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
                        parentImg.add(uploadUrl);
                    }
                    //程序结束时，删除临时文件
                    qiNiuUtils.deleteFile(excelFile);
                }
            }

            TUserInfoEntity userInfoEntity = new TUserInfoEntity();
            userInfoEntity.setAvatarImgUrl(StringUtils.join(parentImg,","));
            userInfoEntity.setMobile(studentVo.getParentConnect());

            String substring = "123456";
            if (studentVo.getParentConnect().length() > 6) {
                substring = studentVo.getParentConnect().substring(studentVo.getParentConnect().length() - 6, studentVo.getParentConnect().length());
            }
            userInfoEntity.setPassword(SHA1.getDigestOfString(substring));
            userInfoEntity.setWechat(studentVo.getParentWechat());
            userInfoEntity.setAddress(studentVo.getAddress());
            userInfoEntity.setUserName(studentVo.getParentName());
            userInfoEntity.setStatus(0);
            //存储家长信息
            userInfoRepository.save(userInfoEntity);

            userStudentEntity.setUserId(userInfoEntity.getUserId());
        }else {
            userStudentEntity.setUserId(studentVo.getParentId());
        }

        userStudentEntity.setStudentId(studentEntity.getId());
        //存储学生家长关系
        userStudentRepository.save(userStudentEntity);

        TClassStudentEntity classStudentEntity = new TClassStudentEntity();
        classStudentEntity.setClassId(studentVo.getClassId());
        classStudentEntity.setStudentId(studentEntity.getId());
        //存储班级学生关系
        classStudentRepository.save(classStudentEntity);

    }

    @Override
    public Page<GartenStudentInfoVo> findTeacherWithconditions(Integer gartenId, Integer classId, Integer courseId, Integer page, Integer size) {
        String sql = GartenStudentDao.getGartenStudentistWithGartenIdAndClassIdAndCourseId(gartenId, classId, courseId);
        Page<GartenStudentInfoVo> resultList = daoUtil.getPagerResultList(sql, page, size, GartenStudentInfoVo.class);
        return resultList;
    }

    @Override
    public List<GartenStudentInfoVo> findStudentWithClassId(Integer classId) {

        String sql = GartenStudentDao.getClassStudentWithClassId(classId);
        List<GartenStudentInfoVo> resultList = daoUtil.getResultList(sql, GartenStudentInfoVo.class);
        return resultList;
    }


}

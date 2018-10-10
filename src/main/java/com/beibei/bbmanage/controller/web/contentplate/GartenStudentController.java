package com.beibei.bbmanage.controller.web.contentplate;

import com.beibei.bbmanage.controller.BaseController;
import com.beibei.bbmanage.handler.Response;
import com.beibei.bbmanage.service.GartenStudentService;
import com.beibei.bbmanage.utils.ExcelImportUtils;
import com.beibei.bbmanage.vo.GartenStudentFormVo;
import com.beibei.bbmanage.vo.GartenStudentInfoVo;
import com.qiniu.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class GartenStudentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private GartenStudentService gartenStudentService;


    @RequestMapping("/import/student/info")
    public ResponseEntity<Object> importStudentInfoExcel(@RequestParam(value = "filename")MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            logger.info("解析excel文件不存在");
        }
        InputStream in = null;
        List<List<Object>> listOb = null;

        in = file.getInputStream();
        Workbook wb = null;
        wb = new ExcelImportUtils().getWorkbook(in, file.getOriginalFilename());
        //新建一个文件
        File tempFile = new File("/Users/haohao/Desktop/textExcelData" + new Date().getTime() + ".xlsx");
        this.readExcelValue(wb,tempFile);

        return Response.success(null,"学生信息导入成功");
    }


    /**
     * 保存学生信息
     * @param infoVo
     * @param studentImage
     * @param parentImage
     * @return
     */
    @RequestMapping("/student/save")
    public ResponseEntity<Object> saveStudent(GartenStudentFormVo infoVo, MultipartFile[] studentImage, MultipartFile[] parentImage) {
        gartenStudentService.saveStudent(infoVo,studentImage,parentImage);
        return Response.success(null,"数据保存成功");
    }

    /**
     * 获取学生列表
     */
    @RequestMapping("/student/list")
    public ResponseEntity<Object> getStudentList(Integer gartenId, Integer classId, Integer courseId,Integer page,Integer size) {
        Page<GartenStudentInfoVo> studentInfoVos = gartenStudentService.findTeacherWithconditions(gartenId, classId, courseId, page, size);
        return Response.success(studentInfoVos,"学生列表获取成功");
    }

    /**	   * 解析Excel里面的数据	   * @param wb	   * @return	   */
    private String readExcelValue(Workbook wb, File tempFile) {
        //错误信息接收器
        String errorMsg = "";
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        int totalRows = sheet.getPhysicalNumberOfRows();           //总列数
        int totalCells = 0;           //得到Excel的列数(前提是有行数)，从第二行算起
        if (totalRows >= 2 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
        }
        List<GartenStudentFormVo> fromVoList = new ArrayList<GartenStudentFormVo>();
        GartenStudentFormVo tempFormVo;
        String br = "<br/>";
        // 循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) {
                errorMsg += br + "第" + (r + 1) + "行数据有问题，请仔细检查！";
                continue;
            }
            tempFormVo = new GartenStudentFormVo();
            String question = "";
            String answer = "";
//         循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                if (null != cell) {
                    String tmpStr = cell.getStringCellValue();
                    tmpStr = StringUtils.isNullOrEmpty(tmpStr)?"0":tmpStr;
                    tempFormVo.setIsExist("1");
                        switch (c) {
                            case 0:
                                tempFormVo.setGartenId(new Integer(tmpStr).intValue());
                                break;
                            case 1:
                                tempFormVo.setStudentName(tmpStr);
                                break;
                            case 2:
                                tempFormVo.setStudentAge(tmpStr);
                                break;
                            case 3:
                                tempFormVo.setStudentGender(tmpStr);
                                break;
                            case 4:
                                tempFormVo.setCourseId(new Integer(tmpStr).intValue());
                                break;
                            case 5:
                                tempFormVo.setClassId(new Integer(tmpStr).intValue());
                                break;
                            case 6:
                                tempFormVo.setStudentImgUrl(tmpStr);
                                break;
                            case 7:
                                tempFormVo.setStudentHobbies(tmpStr);
                                break;
                            case 8:
                                tempFormVo.setParentName(tmpStr);
                                break;
                            case 9:
                                tempFormVo.setAddress(tmpStr);
                                break;
                            case 10:
                                tempFormVo.setParentConnect(tmpStr);
                                break;
                            case 11:
                                tempFormVo.setParentWechat(tmpStr);
                                break;
                            case 12:
                                tempFormVo.setParentImgUrl(tmpStr);
                                break;
                            default:
                                break;
                        }
                } else {
                    rowMessage += "第" + (c + 1) + "列数据有问题，请仔细检查；";
                }
            }
            // 拼接每行的错误提示
            if (!StringUtils.isNullOrEmpty(rowMessage)) {
                errorMsg += br + "第" + (r + 1) + "行，" + rowMessage;
            } else {
                fromVoList.add(tempFormVo);
            }	       }
        // 删除上传的临时文件
        if (tempFile.exists()) {
            tempFile.delete();
        }
        // 全部验证通过才导入到数据库
        if (StringUtils.isNullOrEmpty(errorMsg)) {
            for (GartenStudentFormVo singleFormVo : fromVoList) {
                gartenStudentService.saveStudent(singleFormVo,null,null);

            }
            errorMsg = "导入成功，共" + fromVoList.size() + "条数据！";
        }
        return errorMsg;
    }



}

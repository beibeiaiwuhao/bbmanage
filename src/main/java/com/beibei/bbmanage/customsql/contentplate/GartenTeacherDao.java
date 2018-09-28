package com.beibei.bbmanage.customsql.contentplate;

public class GartenTeacherDao {


    public static String getGartenTeacherListWithGartenIdAndClassIdAndCourseId(Integer gartenId, Integer classId,Integer courseId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(" it.id AS id , ");
        sql.append(" it.garten_id AS gartenId, ");
        sql.append(" it.teacher_name AS teacherName, ");
        sql.append(" it.teacher_mobile AS teacherMobile, ");
        sql.append(" it.teacher_classes AS teacherClasses, ");
        sql.append(" it.teacher_desc AS teacherDesc, ");
        sql.append(" it.wechat, ");
        sql.append(" it.address, ");
        sql.append(" it.avatar_img_url AS avatarImgUrl, ");
        sql.append(" it.status, ");
        sql.append(" it.course_id AS courseId, ");
        sql.append(" it.gender ");
        sql.append(" FROM ");
        sql.append(" t_garten_teacher it ");
        if (classId != null && classId != 0) {
            sql.append(" INNER JOIN t_class_teacher ig on ");
            sql.append(String.format(" ig.class_id=%d ",classId));
            sql.append("AND it.id=ig.teacher_id");
        }
        sql.append(" WHERE 1=1 ");
        if (gartenId != null && gartenId != 0) {
            sql.append(String.format(" AND it.garten_id = %d",gartenId));
        }
        if (courseId != null && courseId != 0) {
            sql.append(String.format(" AND it.course_id = %d",courseId));
        }
        return sql.toString();
    }


}

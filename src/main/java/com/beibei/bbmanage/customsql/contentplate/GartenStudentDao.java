package com.beibei.bbmanage.customsql.contentplate;

public class GartenStudentDao {

    public static String getGartenTeacherListWithGartenIdAndClassIdAndCourseId(Integer gartenId, Integer classId,Integer courseId) {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT ");
        sql.append(" it.id AS id , ");
        sql.append(" it.student_name AS studentName, ");
        sql.append(" it.student_gender AS studentGender, ");
        sql.append(" it.course_name AS courseName, ");
        sql.append(" it.student_age AS studentAge, ");
        sql.append(" it.student_desc AS studentDesc, ");
        sql.append(" it.status, ");
        sql.append(" it.avatar_img_url AS avatarImgUrl, ");
        sql.append(" it.garten_id AS gartenId, ");
        sql.append(" iui.user_name AS userName ");
        sql.append(" FROM ");
        sql.append(" t_garten_student it ");
        sql.append(" INNER JOIN t_user_student ius ON ");
        sql.append(" ius.student_id = it.id ");
        sql.append(" INNER JOIN t_user_info iui ON ");
        sql.append(" iui.user_id = ius.user_id ");

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

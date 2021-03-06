package com.beibei.bbmanage.customsql.contentplate;

public class GartenStudentDao {

    /**
     * 筛选学生条件 的sql语句
     * @param gartenId
     * @param classId
     * @param courseId
     * @return
     */
    public static String getGartenStudentistWithGartenIdAndClassIdAndCourseId(Integer gartenId, Integer classId,Integer courseId) {
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT ");
        sql.append(" it.id AS id , ");
        sql.append(" it.student_name AS studentName, ");
        sql.append(" it.student_gender AS studentGender, ");
        sql.append(" it.student_age AS studentAge, ");
        sql.append(" it.student_desc AS studentDesc, ");
        sql.append(" it.status, ");
        sql.append(" it.avatar_img_url AS avatarImgUrl, ");
        sql.append(" it.garten_id AS gartenId, ");
        sql.append(" iui.user_name AS userName, ");
        sql.append(" igc.course_name AS courseName ");
        sql.append(" FROM ");
        sql.append(" t_garten_student it ");

        if (classId != null && classId != 0) {
            sql.append(" INNER JOIN t_class_student ics ON ");
            sql.append(String.format(" ics.class_id=%d ",classId));
            sql.append("AND it.id=ics.student_id");
        }

        sql.append(" INNER JOIN t_garten_course igc ON ");
        sql.append(" igc.id = it.course_id ");
        sql.append(" INNER JOIN t_user_student ius ON ");
        sql.append(" ius.student_id = it.id ");
        sql.append(" INNER JOIN t_user_info iui ON ");
        sql.append(" iui.user_id = ius.user_id ");
        sql.append(" WHERE 1=1 ");
        if (gartenId != null && gartenId != 0) {
            sql.append(String.format(" AND it.garten_id = %d",gartenId));
        }
        if (courseId != null && courseId != 0) {
            sql.append(String.format(" AND it.course_id = %d",courseId));
        }
        return sql.toString();
    }

    /**
     * 根据班级id查询班级学生
     * @param classId
     * @return
     */
    public static String getClassStudentWithClassId(Integer classId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" tgs.student_name AS studentName, ");
        sql.append(" tgs.student_gender AS studentGender, ");
        sql.append(" tgs.student_age AS studentAge, ");
        sql.append(" tgs.student_desc AS studentDesc, ");
        sql.append(" tgs.avatar_img_url AS avatarImgUrl, ");
        sql.append(" tgc.course_name AS courseName ");
        sql.append(" FROM ");
        sql.append(" t_class_student tcs ");
        sql.append(" INNER JOIN t_garten_student tgs ON tgs.id = tcs.student_id ");
        sql.append(" INNER JOIN t_garten_course tgc ON tgc.id = tgs.course_id ");
        sql.append(" WHERE ");
        sql.append(" 1 = 1 ");
        sql.append(String.format("AND tcs.class_id = %d",classId));
        return sql.toString();
    }



}

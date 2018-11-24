package com.beibei.bbmanage.customsql.contentplate;

/**
 * 园所老师列表
 */
public class GartenTeacherDao {


    /**
     * 筛选sql语句
     * @param gartenId
     * @param classId
     * @param courseId
     * @return
     */
    public static String getGartenTeacherListWithGartenIdAndClassIdAndCourseId(Integer gartenId, Integer classId,Integer courseId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(" it.id AS id , ");
        sql.append(" it.garten_id AS gartenId, ");
        sql.append(" it.teacher_name AS teacherName, ");
        sql.append(" it.teacher_mobile AS teacherMobile, ");
        sql.append(" it.teacher_classes AS teacherClasses, ");
        sql.append(" it.teacher_desc AS teacherDesc, ");
        sql.append(" it.position_name AS positionName, ");
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

    /**
     * 通过登录用户的userId 查询当前用户的学生的所在学校的班级
     * @param userId
     * @return
     */
    public static String getTeacherListWithUserId(Integer userId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" tgt.teacher_name AS teacherName , ");
        sql.append(" tgc.class_name AS className, ");
        sql.append(" tgt.position_name AS positionName, ");
        sql.append(" tgc.id AS classId ");
        sql.append(" FROM ");
        sql.append(" t_user_info tui ");
        sql.append(" INNER JOIN t_user_student tus ON tus.user_id = tui.user_id ");
        sql.append(" INNER JOIN t_garten_student tgs ON tus.student_id = tgs.id ");
        sql.append(" INNER JOIN t_garten_class tgc ON tgc.garten_id = tgs.garten_id ");
        sql.append(" INNER JOIN t_class_teacher tct ON tct.class_id = tgc.id ");
        sql.append(" INNER JOIN t_garten_teacher tgt ON tgt.id = tct.teacher_id ");
        sql.append(" WHERE 1=1  ");
        if ( null != userId && userId != 0) {
            sql.append(String.format(" AND tui.user_id = %d",userId));
        }
        return sql.toString();
    }




}

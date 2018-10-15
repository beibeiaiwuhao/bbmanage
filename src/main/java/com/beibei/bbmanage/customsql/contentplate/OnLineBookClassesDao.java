package com.beibei.bbmanage.customsql.contentplate.onlineBook;

/**
 * 在线预约课程
 */
public class OnLineBookClassesDao {

    /**
     * @param minDate
     * @param maxDate
     * @param userName
     * @param page
     * @param size
     * @return
     */


    public static String getOnlineBookClassesListWithDateAndUserName(String minDate,String maxDate,String userName,Integer page,Integer size) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(" it.id, ");
        sql.append(" it.user_id AS userId, ");
        sql.append(" it.mobile, ");
        sql.append(" it.apponitment_time AS apponitmentTime, ");
        sql.append(" it.apponitment_classes AS apponitmentClasses, ");
        sql.append(" it.status, ");
        sql.append(" it.creator, ");
        sql.append(" it.create_time AS createTime, ");
        sql.append(" it.mender, ");
        sql.append(" it.mend_time AS mendTime, ");
        sql.append(" it.remark, ");
        sql.append(" it.user_name AS userName ");
        sql.append(" FROM ");
        sql.append(" t_appointment_info it ");
        sql.append(" WHERE 1=1 ");
        if (minDate != null &&  !"".equals(minDate)) {
            sql.append(String.format(" AND it.create_time > '%s'",minDate));
        }
        if (maxDate != null &&  !"".equals(maxDate)) {
            sql.append(String.format(" AND it.create_time < '%s'",maxDate));
        }

        if (userName != null && !"".equals(userName)) {
            sql.append(String.format(" AND it.user_name like  '%s%s%s'","%",userName,"%"));
        }

        return sql.toString();
    }




}

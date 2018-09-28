package com.beibei.bbmanage.customsql.contentplate;

/**
 * 通知公告
 */
public class GartenNoticeDao {

    public static String getGartenNoticeWithDate(String minDate,String maxDate,Integer gardenId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(" it.notice_id AS noticeId , ");
        sql.append(" it.garten_id AS gartenId, ");
        sql.append(" it.title, ");
        sql.append(" it.content, ");
        sql.append(" it.public_status AS publicStatus, ");
        sql.append(" it.creator, ");
        sql.append(" it.create_time AS createTime, ");
        sql.append(" it.mender, ");
        sql.append(" it.mend_time AS mendTime, ");
        sql.append(" it.remark, ");

        sql.append(" ig.garden_name AS gardenName");

        sql.append(" FROM ");
        sql.append(" t_garten_notice it ");

        sql.append(" INNER JOIN t_garten_info ig on ");
        sql.append(" it.garten_id=ig.id ");

        sql.append(" WHERE 1=1 ");

        if (minDate != null &&  !"".equals(minDate)) {
            sql.append(String.format(" AND it.create_time > '%s'",minDate));
        }
        if (maxDate != null &&  !"".equals(maxDate)) {
            sql.append(String.format(" AND it.create_time < '%s'",maxDate));
        }
        if (gardenId != null && gardenId != 0) {
            sql.append(String.format(" AND it.garten_id = %d",gardenId));
        }

        return sql.toString();
    }



}

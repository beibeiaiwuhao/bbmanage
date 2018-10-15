package com.beibei.bbmanage.customsql.contentplate;

/**
 * 宝贝视频
 */
public class GartenVideoDao {

    public static String getGartenVideoList(String minDate,String maxDate,Integer gartenId,Integer classId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(" igv.id AS id , ");
        sql.append(" igv.garten_id AS gartenId, ");
        sql.append(" igv.video_name AS videoName, ");
        sql.append(" igv.video_desc AS videoDesc, ");
        sql.append(" igv.video_url AS videoUrl, ");
        sql.append(" igv.class_id AS classId, ");
        sql.append(" igv.create_time AS createTime, ");
        sql.append(" igv.status, ");
        sql.append(" igv.creator, ");
        sql.append(" igi.garden_name AS gartenName, ");
        sql.append(" igc.class_name AS className ");

        sql.append(" FROM ");
        sql.append(" t_garten_video igv ");

        sql.append(" INNER JOIN t_garten_info igi ON igi.id = igv.garten_id ");
        sql.append(" INNER JOIN t_garten_class igc ON igc.id = igv.class_id ");

        sql.append(" WHERE 1=1 ");
        if (minDate != null &&  !"".equals(minDate)) {
            sql.append(String.format(" AND igv.create_time > '%s'",minDate));
        }
        if (maxDate != null &&  !"".equals(maxDate)) {
            sql.append(String.format(" AND igv.create_time < '%s'",maxDate));
        }
        if (classId != null && classId != 0) {
            sql.append(String.format(" AND igv.class_id = %d",classId));
        }
        if (gartenId != null && gartenId != 0) {
            sql.append(String.format(" AND igv.garten_id = %d",gartenId));
        }
        return sql.toString();
    }


}

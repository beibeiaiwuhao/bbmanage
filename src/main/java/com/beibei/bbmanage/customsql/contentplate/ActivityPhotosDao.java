package com.beibei.bbmanage.customsql.contentplate;

/**
 * @author haohao
 * <p>
 * date: 2018年11月06日
 */
public class ActivityPhotosDao {

    /**
     * 根据活动id 获取活动相册
     * @param activityId
     * @return
     */
    public static String getActivityPhotos(Integer activityId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select DISTINCT ");
        sql.append(" tap.activity_id AS activityId, ");
        sql.append(" tga.activity_name AS activityName, ");
        sql.append(" taf.file_url AS fileUrl, ");
        sql.append(" taf.id AS fileId, ");
        sql.append(" taf.file_type AS fileType, ");
        sql.append(" taf.file_size AS fileSize, ");
        sql.append(" taf.create_time AS createTime  ");
        sql.append(" FROM t_activity_photos tap ");
        sql.append(" INNER JOIN t_garten_activity tga ON tga.id = tap.activity_id ");
        sql.append(" INNER JOIN t_activity_file taf ON tap.photo_id = taf.id  ");
        sql.append(" WHERE 1=1 ");
        sql.append(String.format(" AND tap.activity_id = %s ",activityId));
        sql.append(" ORDER BY ");
        sql.append(" taf.create_time DESC ");
        return sql.toString();
    }

    /**
     * 根据gartenId 分别找出该园所下每个活动有多少张照片
     * @param gartenId
     * @return
     */
    public static String getWXActivityIndex(Integer gartenId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select DISTINCT ");
        sql.append(" t.activityName, ");
        sql.append(" t.fileUrl, ");
        sql.append(" t.activityId, ");
        sql.append(" count( t.fileUrl ) AS imgCount  ");
        sql.append(" from ( SELECT DISTINCT ");
        sql.append(" tga.id AS activityId, ");
        sql.append(" tga.activity_name AS activityName, ");
        sql.append(" taf.file_url AS fileUrl  ");
        sql.append(" FROM ");
        sql.append(" t_activity_file taf  ");
        sql.append(String.format(" INNER JOIN t_garten_activity tga ON tga.garten_id = %s ",gartenId));
        sql.append(" INNER JOIN t_activity_photos tap ON tap.activity_id = tga.id ");
        sql.append(" AND taf.id = tap.photo_id ORDER BY taf.create_time DESC ) t ");
        sql.append(" GROUP BY t.activityId ");
        return sql.toString();
    }




}

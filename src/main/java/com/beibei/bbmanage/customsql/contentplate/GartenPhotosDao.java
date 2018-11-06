package com.beibei.bbmanage.customsql.contentplate;

public class GartenPhotosDao {

//    cast(count(1) as varchar)
    public static String getGartenPhotosClass(Integer gartenId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT DISTINCT ");
        sql.append(" t.id AS classId , ");
        sql.append(" t.class_name AS className, ");
        sql.append(" t.photo_url AS photoUrl, ");
        sql.append(" t.create_time AS createTime, ");
        sql.append(" count( t.photo_url ) AS imgCount ");
        sql.append(" FROM ( SELECT DISTINCT ");
        sql.append(" tgc.id, ");
        sql.append(" tgc.class_name, ");
        sql.append(" tgp.photo_url, ");
        sql.append(" tgp.create_time ");
        sql.append(" FROM t_garten_class tgc ");
        sql.append(" LEFT JOIN t_garten_photos tgp ON tgp.class_id = tgc.id ");
        sql.append(" WHERE 1 = 1 ");
        sql.append(String.format(" AND tgc.garten_id = %s ",gartenId));
        sql.append(" ORDER BY tgp.create_time DESC ) t ");
        sql.append(" GROUP BY ");
        sql.append(" t.id ");

        return sql.toString();
    }






}

package com.beibei.bbmanage.customsql.contentplate;

/**
 * @author haohao
 * <p>
 * date: 2018年11月02日
 */
public class GartenRecipeDao {

    /**
     * 根据园所id和日期范围获取食谱列表
     * @param beginDate
     * @param endDate
     * @param gardenId
     * @return
     */
    public static String getGartenRecipeWithDate(String beginDate,String endDate,Integer gardenId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append(" tgr.id , ");
        sql.append(" tgr.avatar_img_url AS avatarImgUrl, ");
        sql.append(" tgr.create_time AS createTime , ");
        sql.append(" tgr.creator , ");
        sql.append(" tgr.garten_id AS gartenId , ");
        sql.append(" tgr.meal_name AS mealName , ");
        sql.append(" tgr.meal_desc AS mealDesc , ");
        sql.append(" tgr.mend_time AS mendTime , ");
        sql.append(" tgr.mender , ");
        sql.append(" tgr.recipe_date AS recipeDate , ");
        sql.append(" tgr.remark , ");
        sql.append(" tgr.status  , ");
        sql.append(" tgr.week_name AS weekName ");

        sql.append(" FROM ");
        sql.append(" t_garten_recipe tgr ");

        sql.append(" WHERE 1=1 ");

        if (beginDate != null &&  !"".equals(beginDate)) {
            sql.append(String.format(" AND tgr.recipe_date > '%s'",beginDate));
        }
        if (endDate != null &&  !"".equals(endDate)) {
            sql.append(String.format(" AND tgr.recipe_date < '%s'",endDate));
        }
        if (gardenId != null && gardenId != 0) {
            sql.append(String.format(" AND tgr.garten_id = %d",gardenId));
        }

        return sql.toString();
    }




}

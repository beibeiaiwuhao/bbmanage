package com.beibei.bbmanage.service.impl;


import com.beibei.bbmanage.customsql.DaoUtil;
import com.beibei.bbmanage.customsql.contentplate.GartenRecipeDao;
import com.beibei.bbmanage.entity.TGartenRecipeEntity;
import com.beibei.bbmanage.repository.GartenRecipeRepository;
import com.beibei.bbmanage.service.GartenRecipeService;
import com.beibei.bbmanage.utils.DateUtil;
import com.beibei.bbmanage.utils.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GartenRecipeServiceImpl implements GartenRecipeService {

    @Autowired
    private GartenRecipeRepository gartenRecipeRepository;

    @Autowired
    private DaoUtil daoUtil;

    @Override
    @Transactional
    public void saveRecipe(TGartenRecipeEntity entity) {
        entity.setStatus(0);
        gartenRecipeRepository.save(entity);
    }

    @Override
    public List<TGartenRecipeEntity> findAllRecipes(Integer gartenId) {
        List<TGartenRecipeEntity> recipeEntities = gartenRecipeRepository.findAllByGartenId(gartenId);
        return recipeEntities;
    }


    @Override
    public TGartenRecipeEntity findOneRecipe(Integer id) {
        return gartenRecipeRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteSlectedRecipe(Integer id) {
        gartenRecipeRepository.delete(id);
    }

    @Override
    public List findAllRecipeByGartenIdAndDate(Integer gartenId, Integer weekType) {
        Map<String, String> weekDays = getWeekDays(weekType);
        String sql = GartenRecipeDao.getGartenRecipeWithDate(weekDays.get("beginDate")+" 00：00：00", weekDays.get("endDate")+" 24：00：00", gartenId);
        List<TGartenRecipeEntity> list = daoUtil.getResultList(sql, TGartenRecipeEntity.class);

        List<Map<String,Object>> weekRecipeList = new ArrayList();
        List<String> tmpArr = new ArrayList<>();
        for (TGartenRecipeEntity entity: list) {
            String dayName = DateUtil.switchCrteaTime(entity.getRecipeDate(), "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日");
            if (!tmpArr.contains(dayName)) {
                tmpArr.add(dayName);
            }
        }

        for (String dayName: tmpArr) {
            List recipeList = new ArrayList();
            Map<String,Object> recipeMap = new HashMap<>();
            int chinaWeek = DateUtil.getChinaWeek(DateUtil.parseDate(DateUtil.switchCrteaTime(dayName, "yyyy年MM月dd日", " yyyy-MM-dd")));
            String[] weekNameList = new String[]{"周一","周二","周三","周四","周五","周六","周日"};
            recipeMap.put("dayDate",dayName);
            recipeMap.put("dayName",weekNameList[chinaWeek-1]);

            for (TGartenRecipeEntity entity: list) {
                String newDate = DateUtil.switchCrteaTime(entity.getRecipeDate(), "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日");
                if (newDate.equals(dayName)) {
                    recipeList.add(entity);
                }
            }
            recipeMap.put("dayRecipeInfo",recipeList);
            weekRecipeList.add(recipeMap);
        }


        return weekRecipeList;
    }

    /**
     * i = 0 是本周
     * i = 1 是下一周
     * i= -1 是上一周
     * 返回的数据是一星期的日期范围
     * @param i
     */
    private static Map<String,String> getWeekDays(int i) {

        Map<String,String> map = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // getTimeInterval(sdf);
        Calendar calendar1 = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayOfWeek) {
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 获得当前日期是一个星期的第几天
        int day = calendar1.get(Calendar.DAY_OF_WEEK);

        //获取当前日期前（下）x周同星几的日期
        calendar1.add(Calendar.DATE, 7*i);
        calendar1.add(Calendar.DATE, calendar1.getFirstDayOfWeek() - day);

        String beginDate = sdf.format(calendar1.getTime());
        calendar1.add(Calendar.DATE, 6);

        String endDate = sdf.format(calendar1.getTime());

        System.out.println(beginDate+" 到 "+endDate);

        map.put("beginDate",beginDate);
        map.put("endDate",endDate);
        return map;
    }


}

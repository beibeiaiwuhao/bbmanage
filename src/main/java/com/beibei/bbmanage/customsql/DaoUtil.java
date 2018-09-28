package com.beibei.bbmanage.customsql;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行sql语句工具类
 * @author Murphy.Chang
 */
@Component
public class DaoUtil {
    private static final Logger logger = LoggerFactory.getLogger(DaoUtil.class);
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 返回集合
     * @author Murphy.Chang
     * @param sql
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getResultList(String sql,Class<T> clazz){
        //获取记录
        List<T> list = new ArrayList<>();
        try {
            Query queryData = entityManager.createNativeQuery(sql);
            queryData.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
//            queryData.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            list = queryData.getResultList();
        } catch(Exception e) {
            logger.info("执行返回集合sql语句出错--{}", e.getMessage());
        } finally {
            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return list;
    }

    /**
     * 返回单个对象
     * @author pcongda
     * @param sql
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getOneResult(String sql,Class<T> clazz){
        T t = null;
        try {
            Query queryData = entityManager.createNativeQuery(sql);
            queryData.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
            t = (T) queryData.getSingleResult();
        } catch(Exception e) {
            logger.info("执行返回sql语句出错--{}", e.getMessage());
        } finally {
            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
        return t;
    }

    /**
     * 获取集合(带分页)
     * @author pcongda
     */
    @SuppressWarnings("unchecked")
    public <T> Page<T> getPagerResultList(String sql, int page,int size, Class<T> clazz){
        List<T> list = null;
        //查询记录条数
        String countSql = "select count(1) as cnt from (" + sql.toString() + ") temp ";
        //创建查询对象
        Query countQuery = entityManager.createNativeQuery(countSql);
        //获取总记录数
        Object totalCount = countQuery.getSingleResult();
        //分页查询
        Query queryData = entityManager.createNativeQuery(sql.toString());
        queryData.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
//        queryData.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        queryData.setFirstResult(page * size);//第一页记录
        queryData.setMaxResults(size);//每页最大记录数
        try {
            list = queryData.getResultList();
        } catch(Exception e) {
            logger.info("执行获取集合(带分页)出错--{}", e.getMessage());
        } finally {
            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
        //设置分页信息
        Page<T> pageInfo = new PageImpl<>(list, new PageRequest(page, size), Long.valueOf(totalCount.toString()));
        return pageInfo;
    }

    /**
     * 返回集合条数
     * @author zhao rui
     * @date 2018年4月18日
     * @param sql  SQL语句
     * @return
     */
    public int getResultCount(String sql){
        logger.info("执行返回集合条数");
        //返回记录条数
        int count = 0;
        //获取记录
        Object objTotalCount = null;
        //获取记录
        try {
            //统计所有数据条数
            Query countQuery = entityManager.createNativeQuery(sql);
            //总记录数据
            objTotalCount = countQuery.getSingleResult();
        } catch(Exception e) {
            logger.info("执行返回集合条数sql语句出错--{}", e.getMessage());
        } finally {
            //关闭entityManager
            if(entityManager != null) {
                entityManager.close();
            }
        }
        if(objTotalCount != null) {
            count = Integer.valueOf(objTotalCount.toString());
        }

        return count;
    }


}


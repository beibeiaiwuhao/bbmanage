package com.beibei.bbmanage.controller;




import com.beibei.bbmanage.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     *
     * 获取分页页面查询条件公司.
     *
     * @author zhao rui
     * @param request
     * @param queryParams 页面查询参数
     * @return
     */
    public Map<String, Object> getQueryCondition(HttpServletRequest request, String[] queryParams) {
        Map<String, Object> queryConditionMap = new HashMap<>();
        if(queryParams!=null && queryParams.length>0) {
            for (String item : queryParams) {
                if(!StringUtil.isNull(request.getParameter(item))) {
                    queryConditionMap.put(item, request.getParameter(item).replaceAll("'", ""));
                }
            }
        }
        //分页参数
        if(!StringUtil.isNull(request.getParameter("limit"))) {
            queryConditionMap.put("limit", request.getParameter("limit"));
        }

        if(!StringUtil.isNull(request.getParameter("page"))) {
            queryConditionMap.put("page", request.getParameter("page"));
        }

        logger.info("查询参数:{}", queryConditionMap.toString());
        return queryConditionMap;
    }

}

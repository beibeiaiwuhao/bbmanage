package com.beibei.bbmanage.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    /**
     * 注销成功的回调
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        super.setDefaultTargetUrl("/web/login/login.html?logout");
        super.onLogoutSuccess(request, response, authentication);
    }
}

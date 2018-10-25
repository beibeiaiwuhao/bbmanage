package com.beibei.bbmanage.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /**
     * 登录失败的回调
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        super.setDefaultFailureUrl("/login.html?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}

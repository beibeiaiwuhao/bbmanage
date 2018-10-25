package com.beibei.bbmanage.security.handler;

import com.beibei.bbmanage.security.entity.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     * 登录成功的回调
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SecurityUser userDetail = (SecurityUser) authentication.getPrincipal();

        HttpSession session = request.getSession();
        session.setAttribute("userDetail", userDetail);
//		SecurityUser userDetail1 = (SecurityUser) session.getAttribute("userDetail");
//		System.out.println("++++++++++++++++++++++++"+userDetail1.getUsername());
//		System.out.println("-------------------------"+userDetail.getUsername());
//		System.out.println(request.getSession().getId());

        super.setDefaultTargetUrl("/index");

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

package com.beibei.bbmanage.security.filter;

import com.beibei.bbmanage.handler.GeetestConfig;
import com.beibei.bbmanage.security.service.CaptchaAuthenticationProvider;
import com.beibei.bbmanage.utils.GeetestLib;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class CaptchaAuthenticationFilter extends GenericFilterBean {

    //验证码拦截路径
    private static final String CODE_ANT_URL = "/login";
    private static final String SPRING_SECURITY_FORM_CAPTCHA_IS_VERIFY_KEY = "captchaIsVerify";

    private String captchaIsVerifyParameter = SPRING_SECURITY_FORM_CAPTCHA_IS_VERIFY_KEY;

    private boolean postOnly = true;

    //请求路径匹配
    private RequestMatcher requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(CODE_ANT_URL, "POST");
    //设置验证失败重定向路径
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("/login.html?unverified");


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 不是需要过滤的路径，执行下一个过滤器
        if (!requiresAuthentication(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authResult;
        try {
            authResult = this.attemptAuthentication(request, response);
            if (authResult == null) {
                return;
            }

        } catch (InternalAuthenticationServiceException failed) {
            return;
        } catch (AuthenticationException failed) {
            //Authentication failed
            unsuccessfulAuthentication(request, response, failed);
            return;
        }

        //认证成功，执行下个过滤器
        filterChain.doFilter(request, response);
    }

    private Authentication attemptAuthentication(HttpServletRequest request,
                                                 HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        //获取验证码
//		Object captchaIsVerify = obtainCaptchaIsVerify(request);
        Object captchaIsVerify= checkVertifyCode(request);

        if (captchaIsVerify == null) {
            captchaIsVerify = "";
        }

        captchaIsVerify = captchaIsVerify.toString().trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(captchaIsVerify, null);

        return new CaptchaAuthenticationProvider().authenticate(authRequest);
    }

    /**
     * 比较需要过滤的请求路径
     *
     * @param request
     * @param response
     * @return
     */
    protected boolean requiresAuthentication(HttpServletRequest request,
                                             HttpServletResponse response) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    /**
     * 处理验证码认证失败
     * 参考 {@link org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter}
     * Default behaviour for unsuccessful authentication.
     * <ol>
     * <li>Clears the {@link SecurityContextHolder}</li>
     * <li>Stores the exception in the session (if it exists or
     * <tt>allowSesssionCreation</tt> is set to <tt>true</tt>)</li>
     * <li>Informs the configured <tt>RememberMeServices</tt> of the failed login</li>
     * <li>Delegates additional behaviour to the {@link AuthenticationFailureHandler}.</li>
     * </ol>
     */
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    /**
     * 是否已经验证
     * @param request
     * @return
     */
    protected Object obtainCaptchaIsVerify(HttpServletRequest request) {
        return request.getSession().getAttribute(captchaIsVerifyParameter);
    }


    protected Object checkVertifyCode(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String) request.getSession().getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
//			System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
//			System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
//			System.out.println(gtResult);
        }

        // 验证成功
        return gtResult == 1?"yes":"flase";
    }

}

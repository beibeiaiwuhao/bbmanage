package com.beibei.bbmanage.security.config;

import com.beibei.bbmanage.security.filter.CaptchaAuthenticationFilter;
import com.beibei.bbmanage.security.handler.CustomLoginFailureHandler;
import com.beibei.bbmanage.security.handler.CustomLoginSuccessHandler;
import com.beibei.bbmanage.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws  Exception {
        //添加自定义验证码过滤器
        http.addFilterBefore(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers()//允许同源iframe访问
                .frameOptions()
                .sameOrigin();

        http
                .authorizeRequests()//拦截页面
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()//全部页面都要验证
                .and()
                .formLogin()
                .usernameParameter("userName")
                .passwordParameter("passWord")
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")//登录成功后默认跳转到"/hello"
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.sessionManagement()
                .sessionFixation()
                .changeSessionId()
                .invalidSessionUrl("/login.html?invalid")//Session失效
                .maximumSessions(1)//只能同时一个人在线
                .sessionRegistry(sessionRegistry())
                .expiredUrl("/login.html?expired");

        //禁用csrf - 使用自定义登录页面
        http.csrf().disable();

    }

    /**
     * session管理器
     * @return
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(new ShaPasswordEncoder());

    }


    /**
     * Web层面的拦截，用来跳过的资源
     * @author Murphy.Chang
     * @return
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        //静态资源
        web.ignoring().antMatchers("/static/**");//静态资源
        web.ignoring().antMatchers("/**/favicon.ico");//网站图标
        //url
        web.ignoring().antMatchers("/login.html");//登录页面
        web.ignoring().antMatchers("/gt/**");//极验验证码接口
    }

    /**
     * 自定义验证码过滤器
     * @return
     */
    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter(){
        return new CaptchaAuthenticationFilter();
    }

    /**
     * 登录成功管理器
     * @author Murphy.Chang
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    /**
     * 登录失败处理
     * @author Murphy.Chang
     * @return
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomLoginFailureHandler();
    }



}

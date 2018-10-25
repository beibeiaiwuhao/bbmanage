package com.beibei.bbmanage.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author haohao
 * <p>
 * date: 2018年10月24日
 */
public class CaptchaAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String captchaIsVerify = token.getPrincipal().toString();

        if(captchaIsVerify.equals("")){
            throw new BadCredentialsException("please verify first!");
        }

        if(!"yes".equals(captchaIsVerify)){
            throw new BadCredentialsException("please verify first!");
        }

        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }
}

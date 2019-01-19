package com.whc.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName JwtToken
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/5 15:59
 * @Version 1.0
 */
public class JwtToken implements AuthenticationToken {

    //密钥
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

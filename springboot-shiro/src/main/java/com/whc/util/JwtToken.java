package com.whc.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt token
 *
 * @author Administrator
 * @date 2019/1/5 15:59
 */
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -2473879284422706611L;
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

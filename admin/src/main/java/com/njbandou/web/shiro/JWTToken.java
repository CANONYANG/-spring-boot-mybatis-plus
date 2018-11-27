package com.njbandou.web.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Company: NJBandou
 * @Author: D丶Cheng
 * @Description:
 * @Date: 2018/4/18 上午11:17
 */
public class JWTToken implements AuthenticationToken{

    private String token;

    public JWTToken(String token) {
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

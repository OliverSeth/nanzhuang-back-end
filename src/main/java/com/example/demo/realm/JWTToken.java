package com.example.demo.realm;

import org.apache.shiro.authc.AuthenticationToken;

import java.lang.ref.PhantomReference;

/**
 * Created by Oliver Seth on 2020/2/3 15:36
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    JWTToken(String token){
        this.token=token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public String getCredentials() {
        return token;
    }
}

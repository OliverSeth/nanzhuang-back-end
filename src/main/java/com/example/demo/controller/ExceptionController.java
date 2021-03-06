package com.example.demo.controller;

import com.example.demo.utils.LogUtils;
import com.example.demo.utils.Result;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Oliver Seth on 2020/2/3 16:02
 */
@RestControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        LogUtils.getErrorLog("Shiro的异常", e);
        return Result.exception(-1, e.toString(), "Shiro的异常");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401() {
        LogUtils.getWarnLog("无权限");
        return Result.exception(-2, "UnauthorizedException", "无权限");
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public Result handleTokenException(AuthenticationException e) {
        LogUtils.getErrorLog("AuthenticationException", e);
        return Result.exception(-3, e.toString(), "AuthenticationException");
    }
}

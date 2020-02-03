package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.realm.JWTToken;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Oliver Seth on 2020/1/31 23:23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/auth")
    public String auth(int code){
        return code==1?"未登录":"未授权";
    }

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "role", value = "角色", required = true, dataType = "String")
    })
    public Result register(String userName, String password, String role) {
        String salt=userName+ System.currentTimeMillis();
        if(userName==null|| userName.equals("")){
            return Result.failed(1,"UserName is empty.");
        }
        if(password==null|| password.equals("")){
            return Result.failed(2,"Password is empty.");
        }
        try {
            User user=userService.findByUserName(userName);
            if(user==null) {
                userService.save(new User(userName, password, salt, role));
                return Result.success("Success registration.");
            }else {
                return Result.failed(3,"User exists.");
            }
        }catch (Exception e){
            return Result.exception(99,e.toString());
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public Result login(String userName,String password){
        Subject subject= SecurityUtils.getSubject();
        String token = JWTUtils.sign(userName, password);
        subject.login(new JWTToken(token));
        return null;
    }
}

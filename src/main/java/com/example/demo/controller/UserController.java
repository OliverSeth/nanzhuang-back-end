package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.realm.JWTUtils;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.Result;
import com.example.demo.utils.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Oliver Seth on 2020/1/31 23:23
 */
@Api(tags = {"用户管理接口"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    @ApiOperation(value = "不要用这个接口", notes = "不要用这个接口", hidden = true)
    public Result auth(int code) {
        String msg = (code == 1) ? "未登录" : "未授权";
        return Result.failed(ConstantCode.UNAUTHORIZED_CODE, msg);
    }

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result register(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        String role = jsonObject.getString("role");
        String salt = userName + System.currentTimeMillis();
        if (userName == null || userName.equals("")) {
            LogUtils.getWarnLog("UserName is empty.");
            return Result.failed(1, "UserName is empty.");
        }
        if (password == null || password.equals("")) {
            LogUtils.getWarnLog(userName, "Password is empty.");
            return Result.failed(2, "Password is empty.");
        }
        try {
            User user = userService.findByUserName(userName);
            if (user == null) {
                password = (new Md5Hash(password, salt, ConstantCode.HASH_ITERATION)).toString();
                userService.save(new User(userName, password, salt, role));
                LogUtils.getInfoLog(userName, "registered successfully.");
                return Result.success("Success registration.");
            } else {
                LogUtils.getWarnLog("User exists.");
                return Result.failed(3, "User exists.");
            }
        } catch (Exception e) {
            LogUtils.getErrorLog(userName, "register" + e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @ResponseBody
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public Result login(@RequestBody JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        if (userName == null || userName.equals("")) {
            LogUtils.getWarnLog("UserName is empty.");
            return Result.failed(1, "UserName is empty.");
        }
        try {
            User user = userService.findByUserName(userName);
            if (user == null) {
                LogUtils.getWarnLog("Invalid username");
                return Result.failed(2, "Invalid username");
            }
            String pass = (new Md5Hash(password, user.getSalt(), ConstantCode.HASH_ITERATION)).toString();
            if (user.getPassword().equals(pass)) {
                String token = JWTUtils.sign(userName, pass);
                Map<String, String> userInfo = new HashMap<>();
                userInfo.put("token", token);
                userInfo.put("userId", user.getUserId().toString());
                LogUtils.getInfoLog(userName, "login successfully.");
                return Result.success(userInfo);
            } else {
                LogUtils.getWarnLog(userName, "Wrong Password.");
                return Result.failed(3, "Wrong Password.");
            }
        } catch (Exception e) {
            LogUtils.getErrorLog(userName, "login", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
//        Subject subject= SecurityUtils.getSubject();
//        String token = JWTUtils.sign(userName, password);
//        subject.login(new JWTToken(token));
//        return null;
    }

    @PostMapping("/change-password")
    @ApiOperation(value = "修改密码")
    public Result changePassword(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        String username = (String) request.getAttribute("username");
        try {
            User user = userService.findByUserName(username);
            Integer id = user.getUserId();
            String password = user.getPassword();
            String salt = user.getSalt();
            String oldPass = jsonObject.getString("oldPassword");
            String newPass = jsonObject.getString("newPassword");
            if (password.equals((new Md5Hash(oldPass, salt, ConstantCode.HASH_ITERATION)).toString())) {
                salt = username + System.currentTimeMillis();
                password = (new Md5Hash(newPass, salt, ConstantCode.HASH_ITERATION)).toString();
                userService.changePassword(id, password, salt);
                String token = JWTUtils.sign(username, password);
                Map<String, String> res = new HashMap<>();
                res.put("token", token);
                LogUtils.getInfoLog(username, "change password successfully.");
                return Result.success(res);
            } else {
                LogUtils.getWarnLog(username, "wrong password.");
                return Result.failed(1, "Wrong password.");
            }
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "change password", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @ApiOperation(value = "查询用户信息")
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        User user = userService.findByUserName(username);
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", user.getUserId().toString());
        userInfo.put("username", user.getUserName());
        userInfo.put("role", user.getRoleName());
        return Result.success(userInfo);
    }
}

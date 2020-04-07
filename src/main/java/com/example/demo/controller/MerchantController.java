package com.example.demo.controller;

import com.example.demo.domain.Merchant;
import com.example.demo.domain.User;
import com.example.demo.dto.MerchantDTO;
import com.example.demo.service.MerchantService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver Seth on 2020/2/15 16:00
 */
@Api(tags = {"商户管理接口"})
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @RequiresRoles("merchant")
    @ApiOperation(value = "商户注册", notes = "限merchant用户访问")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "ownerName", value = "商户经营者", required = true),
            @ApiImplicitParam(paramType = "query", name = "businessCode", value = "商企代码", required = true),
            @ApiImplicitParam(paramType = "query", name = "uniqueCode", value = "唯一编码", required = true),
    })
    public Result merchantRegister(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String ownerName = request.getParameter("ownerName");
        String businessCode = request.getParameter("businessCode");
        String uniqueCode = request.getParameter("uniqueCode");
        if (ownerName == null || ownerName.equals("")) {
            LogUtils.getWarnLog(username, "empty merchant owner name.");
            return Result.failed(1, "Empty merchant owner name.");
        }
        if (businessCode == null || businessCode.equals("")) {
            LogUtils.getWarnLog(username, "empty business code.");
            return Result.failed(2, "Empty business code.");
        }
        if (uniqueCode == null || uniqueCode.equals("")) {
            LogUtils.getWarnLog(username, "empty unique code.");
            return Result.failed(3, "Empty unique code.");
        }
        try {
            if (merchantService.findByUsername(username) != null) {
                LogUtils.getWarnLog(username, "is already a merchant.");
                return Result.failed(4, "User is already a merchant.");
            }
            if (merchantService.findByBusinessCode(businessCode) != null) {
                LogUtils.getWarnLog(username, "Business code has been registered.");
                return Result.failed(5, "Business code has been registered.");
            }
            if (merchantService.findByUniqueCode(uniqueCode) != null) {
                LogUtils.getWarnLog(username, "Unique code has been registered.");
                return Result.failed(6, "Unique code has been registered.");
            }
            Merchant merchant = merchantService.save(new Merchant(username, ownerName, businessCode, uniqueCode));
            Map<String, String> map = new HashMap<>();
            map.put("merchantId", merchant.getMerchantId().toString());
            LogUtils.getInfoLog(username, "merchant registered successfully.");
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "register merchant.", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("/info")
    @ApiOperation("商户信息")
    public Result merchantInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        try {
            Merchant merchant = merchantService.findByUsername(username);
            if (merchant == null) {
                LogUtils.getWarnLog(username, "merchant not exist");
                return Result.success(new Merchant());
            }
            LogUtils.getInfoLog(username, "get information for merchant " + merchant.getOwnerName());
            return Result.success(merchant);
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "get merchant information", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("/删除商户")
    @ApiImplicitParam(paramType = "query", name = "merchantId", value = "商户id", required = true, dataType = "int")
    public Result deleteMerchant(HttpServletRequest request) {
        Integer merchantId = Integer.valueOf(request.getParameter("merchantId"));
        String username = (String) request.getAttribute("username");
        try {
            User user = userService.findByUserName(username);
            Merchant merchant = merchantService.findByMerchantId(merchantId);
            if (user.getRoleName().equals("admin") || merchant.getUsername().equals(username)) {
                merchantService.deleteByMerchantId(merchantId);
                LogUtils.getInfoLog(username, "delete merchant " + merchantId);
                return Result.success();
            } else {
                LogUtils.getWarnLog(username, "has no access to delete merchant " + merchantId);
                return Result.failed(1, "无权限");
            }
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "delete merchant " + merchantId.toString(), e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("/list")
    @ApiOperation("获取所有商户信息")
    public Result findAllMerchant() {
        try {
            List<MerchantDTO> list = merchantService.findAllMerchantDTO();
            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("length", list.size());
            LogUtils.getInfoLog("", "get all merchant information.");
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("get all merchant information", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }
}

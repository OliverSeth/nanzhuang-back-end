package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver Seth on 2020/2/17 16:59
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    @RequiresRoles(value = {"merchant", "admin"}, logical = Logical.OR)
    @ApiOperation("添加产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "productCode", value = "产品代码", required = true),
            @ApiImplicitParam(paramType = "query", name = "productName", value = "产品名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "typeLevel", value = "类别等级", required = true),
    })
    public Result addProduct(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        String typeLevel = request.getParameter("typeLevel");
        if (productCode == null || productCode.equals("")) {
            LogUtils.getWarnLog(username, "submit empty product code when add product.");
            return Result.failed(1, "Empty product code.");
        }
        if (productName == null || productName.equals("")) {
            LogUtils.getWarnLog(username, "submit empty product name when add product.");
            return Result.failed(2, "Empty product name.");
        }
        if (typeLevel == null || typeLevel.equals("")) {
            LogUtils.getWarnLog(username, "submit empty type level when add product.");
            return Result.failed(3, "Empty type level.");
        }
        try {
            if (productService.findByProductCode(productCode) != null) {
                LogUtils.getWarnLog(username, "product code exists.");
                return Result.failed(4, "Product code exists.");
            }
            if (productService.findByProductName(productName) != null) {
                LogUtils.getWarnLog(username, "product name exists.");
                return Result.failed(5, "Product name exists.");
            }
            productService.save(new Product(productCode, productName, typeLevel));
            Product product = productService.findByProductCode(productCode);
            Map<String, String> map = new HashMap<>();
            map.put("productId", product.getProductId().toString());
            LogUtils.getInfoLog(username, "add a product " + product.getProductId().toString());
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "add a product", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }
}

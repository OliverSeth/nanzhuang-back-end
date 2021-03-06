package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver Seth on 2020/2/17 16:59
 */
@Api(tags = {"产品管理接口"})
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
            @ApiImplicitParam(paramType = "query", name = "daleiId", value = "产品大类id", dataType = "int"),
    })
    public Result addProduct(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String productCode = request.getParameter("productCode");
        String productName = request.getParameter("productName");
        String typeLevel = request.getParameter("typeLevel");
        Integer daleiId;
        if (request.getParameter("daleiId") == null) {
            daleiId = null;
        } else {
            daleiId = Integer.parseInt(request.getParameter("daleiId"));
        }
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
            Product product;
            if (typeLevel.equals("Big")) {
                product = productService.save(new Product(productCode, productName, typeLevel));
            } else if (typeLevel.equals("Middle")) {
                Product bigProduct = productService.findByProductId(daleiId);
                if (bigProduct == null) {
                    LogUtils.getWarnLog(username, "Dalei id not exists");
                    return Result.failed(6, "Dalei id not exists.");
                } else if (!bigProduct.getTypeLevel().equals("Big")) {
                    LogUtils.getWarnLog(username, "Dalei id is invalid");
                    return Result.failed(7, "Dalei id is invalid");
                } else {
                    product = productService.save(new Product(productCode, productName, typeLevel, daleiId));
                }
            } else {
                LogUtils.getWarnLog(username, "add product but type level is" + typeLevel);
                return Result.failed(8, "Type level must be Big or Middle.");
            }
            Map<String, String> map = new HashMap<>();
            map.put("productId", product.getProductId().toString());
            LogUtils.getInfoLog(username, "add a product " + product.getProductId().toString());
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "add a product", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("/get")
    @ApiImplicitParam(paramType = "query", name = "productId", value = "产品id", required = true, dataType = "int")
    @ApiOperation("获取产品信息")
    public Result findByProductId(Integer productId) {
        try {
            Product product = productService.findByProductId(productId);
            LogUtils.getInfoLog("", "find product by product id.");
            return Result.success(product);
        } catch (Exception e) {
            LogUtils.getErrorLog("find product by product id", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("/get-list")
    @ApiOperation("按类别等级获取产品列表")
    @ApiImplicitParam(paramType = "query", name = "typeLevel", value = "类别等级")
    public Result findByTypeLevel(String typeLevel) {
        List<Product> productList;
        try {
            if (typeLevel == null || typeLevel.equals("")) {
                productList = productService.findAll();
            } else {
                productList = productService.findAllByTypeLevel(typeLevel);
            }
            LogUtils.getInfoLog("", "get product list by type level.");
            Map<String, List<Product>> map = new HashMap<>();
            map.put("productList", productList);
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("get product list by type level", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @DeleteMapping("delete")
    @RequiresRoles(value = {"admin", "merchant"}, logical = Logical.OR)
    @ApiOperation("删除产品")
    @ApiImplicitParam(paramType = "query", name = "productId", value = "产品id", required = true)
    public Result deleteProduct(HttpServletRequest request, Integer productId) {
        String username = (String) request.getAttribute("username");
        try {
            if (productService.findByProductId(productId).getTypeLevel().equals("Big")) {
                List<Product> zhongleiList = productService.findAllByDaleiId(productId);
                for (Product product : zhongleiList) {
                    productService.deleteByProductId(product.getProductId());
                }
            }
            productService.deleteByProductId(productId);
            LogUtils.getInfoLog(username, "delete product " + productId.toString());
            return Result.success();
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "delete product " + productId.toString(), e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("get-mid")
    @ApiOperation("按大类id获取中类")
    @ApiImplicitParam(paramType = "query", name = "productId", value = "产品大类id", required = true)
    public Result getMiddleProduct(Integer productId) {
        try {
            List<Product> list = productService.findAllByDaleiId(productId);
            LogUtils.getInfoLog("", "get middle product list by dalei id");
            Map<String, List<Product>> map = new HashMap<>();
            map.put("productList", list);
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("get middle product list by dalei id", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }
}

package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.SaleRecord;
import com.example.demo.domain.User;
import com.example.demo.service.*;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.PeriodUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver Seth on 2020/2/18 17:33
 */
@RestController
@RequestMapping("sale-record")
public class SaleRecordController {
    @Autowired
    UserService userService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @Autowired
    SaleRecordService saleRecordService;

    @Autowired
    PriceIndexService priceIndexService;

    @GetMapping("/all")
    @ApiOperation("分页获取所有销售记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int"),
    })
    public Result findAllSaleRecord(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        try {
            List<SaleRecord> page = saleRecordService.findAll(pageNum, pageSize);
            Long length = saleRecordService.countAll();
            LogUtils.getInfoLog("", "get all sale record");
            Map<String, Object> map = new HashMap<>();
            map.put("list", page);
            map.put("length", length);
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("get all sale record", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("/query-records")
    @ApiOperation("按条件查询销售记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "merchantName", value = "商户经营者"),
            @ApiImplicitParam(paramType = "query", name = "businessCode", value = "商企代码"),
            @ApiImplicitParam(paramType = "query", name = "recodeUsername", value = "录入用户"),
            @ApiImplicitParam(paramType = "query", name = "uniqueCode", value = "唯一编码"),
            @ApiImplicitParam(paramType = "query", name = "productDaleiName", value = "产品大类"),
            @ApiImplicitParam(paramType = "query", name = "productZhongleiName", value = "产品中类"),
            @ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int"),
    })
    public Result findSaleRecordByQuerys(String merchantName, String businessCode, String recodeUsername,
                                         String uniqueCode, String productDaleiName, String productZhongleiName,
                                         Integer startTime, Integer endTime, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        if (pageNum <= 0 || pageSize <= 0) {
            LogUtils.getWarnLog("Invalid pageNum or pageSize.");
            return Result.failed(1, "Invalid pageNum or pageSize.");
        }
        merchantName = merchantName.trim().isEmpty() ? null : merchantName;
        businessCode = businessCode.trim().isEmpty() ? null : businessCode;
        recodeUsername = recodeUsername.trim().isEmpty() ? null : recodeUsername;
        uniqueCode = uniqueCode.trim().isEmpty() ? null : uniqueCode;
        productDaleiName = productDaleiName.trim().isEmpty() ? null : productDaleiName;
        productZhongleiName = productZhongleiName.trim().isEmpty() ? null : productZhongleiName;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "period");
        try {
            List<SaleRecord> list = saleRecordService.findSaleRecordByQuerys(merchantName, businessCode, recodeUsername,
                    uniqueCode, productDaleiName, productZhongleiName, startTime, endTime, pageable);
            Map<String, List<SaleRecord>> map = new HashMap<>();
            map.put("list", list);
            LogUtils.getInfoLog("", "find sale record by query.");
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("find sale record by query.", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @PostMapping("add")
    @RequiresRoles(value = {"admin", "merchant"}, logical = Logical.OR)
    @ApiOperation("添加销售记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "merchantName", value = "商户经营者", required = true),
            @ApiImplicitParam(paramType = "query", name = "businessCode", value = "商企代码", required = true),
            @ApiImplicitParam(paramType = "query", name = "uniqueCode", value = "唯一编码", required = true),
            @ApiImplicitParam(paramType = "query", name = "periodYear", value = "期数年", required = true),
            @ApiImplicitParam(paramType = "query", name = "periodMonth", value = "期数月", required = true),
            @ApiImplicitParam(paramType = "query", name = "periodDays", value = "期数日", required = true),
            @ApiImplicitParam(paramType = "query", name = "brand", value = "品牌", required = true),
            @ApiImplicitParam(paramType = "query", name = "region", value = "区域", required = true),
            @ApiImplicitParam(paramType = "query", name = "productDaleiCode", value = "产品大类代码", required = true),
            @ApiImplicitParam(paramType = "query", name = "productDaleiName", value = "产品大类名", required = true),
            @ApiImplicitParam(paramType = "query", name = "productDaleiSales", value = "大类本旬销售额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "productZhongleiCode", value = "产品中类代码", required = true),
            @ApiImplicitParam(paramType = "query", name = "productZhongleiName", value = "产品中类名称", required = true),
            @ApiImplicitParam(paramType = "query", name = "productZhongleiSalePrice", value = "中类本旬销售额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "productZhongleiSaleNumber", value = "中类本旬销售量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "representationNumber", value = "规格代表品货号", required = true),
            @ApiImplicitParam(paramType = "query", name = "currentPrice", value = "本旬价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType = "query", name = "currentSales", value = "本旬销量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "A", value = "主材质面料"),
            @ApiImplicitParam(paramType = "query", name = "B", value = "面料类型"),
            @ApiImplicitParam(paramType = "query", name = "C", value = "领型"),
            @ApiImplicitParam(paramType = "query", name = "D", value = "下摆"),
            @ApiImplicitParam(paramType = "query", name = "E", value = "门襟"),
            @ApiImplicitParam(paramType = "query", name = "F", value = "款式细节"),
            @ApiImplicitParam(paramType = "query", name = "note", value = "备注"),
    })
    public Result addSaleRecord(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        String merchantName = request.getParameter("merchantName");
        String businessCode = request.getParameter("businessCode");
        String uniqueCode = request.getParameter("uniqueCode");
        String periodYear = request.getParameter("periodYear");
        String periodMonth = request.getParameter("periodMonth");
        String periodDays = request.getParameter("periodDays");
        if (!PeriodUtils.validYear(periodYear)) {
            LogUtils.getWarnLog(username, "invalid periodYear when add sale record");
            return Result.failed(1, "Invalid periodYear.");
        }
        if (!PeriodUtils.validMonth(periodMonth)) {
            LogUtils.getWarnLog(username, "invalid periodMonth when add sale record");
            return Result.failed(2, "Invalid periodMonth.");
        }
        if (!PeriodUtils.validDay(periodDays)) {
            LogUtils.getWarnLog(username, "invalid periodDays when add sale record");
            return Result.failed(3, "Invalid periodDays.");
        }
        String brand = request.getParameter("brand");
        String region = request.getParameter("region");
        String productDaleiCode = request.getParameter("productDaleiCode");
        String productDaleiName = request.getParameter("productDaleiName");
        double productDaleiSales = Double.parseDouble(request.getParameter("productDaleiSales"));
        String productZhongleiCode = request.getParameter("productZhongleiCode");
        String productZhongleiName = request.getParameter("productZhongleiName");
        double productZhongleiSalePrice = Double.parseDouble(request.getParameter("productZhongleiSalePrice"));
        Integer productZhongleiSaleNumber = Integer.parseInt(request.getParameter("productZhongleiSaleNumber"));
        String representationNumber = request.getParameter("representationNumber");
        double currentPrice = Double.parseDouble(request.getParameter("currentPrice"));
        Integer currentSales = Integer.parseInt(request.getParameter("currentSales"));
        String A = request.getParameter("A") == null ? "" : request.getParameter("A");
        String B = request.getParameter("B") == null ? "" : request.getParameter("B");
        String C = request.getParameter("C") == null ? "" : request.getParameter("C");
        String D = request.getParameter("D") == null ? "" : request.getParameter("D");
        String E = request.getParameter("E") == null ? "" : request.getParameter("E");
        String F = request.getParameter("F") == null ? "" : request.getParameter("F");
        String note = request.getParameter("note") == null ? "" : request.getParameter("note");
        Long recordTimeStamp = System.currentTimeMillis() / 1000;
        try {
            Product bigProduct = productService.findByProductCode(productDaleiCode);
            if (bigProduct == null) {
                LogUtils.getWarnLog(username, "productDaleiCode not exist when add sale record");
                return Result.failed(4, "ProductDaleiCode not exist.");
            }
            if (!bigProduct.getProductName().equals(productDaleiName)) {
                LogUtils.getWarnLog(username, "productDaleiCode and productDaleiName not match when add sale record");
                return Result.failed(5, "ProductDaleiCode and productDaleiName not match.");
            }
            Product middleProduct = productService.findByProductCode(productZhongleiCode);
            if (middleProduct == null) {
                LogUtils.getWarnLog(username, "productZhongleiCode not exist when add sale record");
                return Result.failed(6, "ProductZhongleiCode not exist.");
            }
            if (!middleProduct.getProductName().equals(productZhongleiName)) {
                LogUtils.getWarnLog(username, "productZhongleiCode and productZhongleiName not match when add sale record");
                return Result.failed(7, "ProductZhongleiCode and productZhongleiName not match.");
            }
            if (!middleProduct.getDaleiId().equals(bigProduct.getProductId())) {
                LogUtils.getWarnLog(username, "productZhonglei not belong to productDalei when add sale record");
                return Result.failed(8, "ProductZhonglei not belong to productDalei.");
            }
            if (userService.findByUserName(username).getRoleName().equals("merchant") &&
                    !merchantService.findByUsername(username).getOwnerName().equals(merchantName)) {
                LogUtils.getWarnLog(username, "has no permission to add");
                return Result.failed(9, "No permission.");
            }
            Integer period = Integer.parseInt(periodYear + periodMonth + periodDays);
            SaleRecord saleRecord = saleRecordService.save(new SaleRecord(username, periodYear, periodMonth, periodDays,
                    period, brand, region, merchantName, businessCode, uniqueCode, productDaleiCode, productDaleiName,
                    productDaleiSales, productZhongleiCode, productZhongleiName, productZhongleiSalePrice,
                    productZhongleiSaleNumber, representationNumber, currentPrice, currentSales, A, B, C, D, E, F, note,
                    recordTimeStamp));
            return Result.success(saleRecord);
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "add sale record", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @DeleteMapping("/delete")
    @RequiresRoles(value = {"admin", "merchant"}, logical = Logical.OR)
    @ApiOperation("删除销售记录")
    public Result deleteSaleRecord(HttpServletRequest request, @RequestParam(value = "saleRecordIds[]") List<Integer> saleRecordIds) {
        String username = (String) request.getAttribute("username");
        String role = userService.findByUserName(username).getRoleName();
        String uniqueCode = null;
        if (role.equals("merchant")) {
            uniqueCode = merchantService.findByUsername(username).getUniqueCode();
        }
        try {
            for (Integer saleRecordId : saleRecordIds) {
                if (role.equals("merchant")) {
                    String uniqueCodeInRecord = saleRecordService.findBySaleRecordId(saleRecordId).getUniqueCode();
                    if (!uniqueCodeInRecord.equals(uniqueCode)) {
                        continue;
                    }
                }
                saleRecordService.deleteBySaleRecordId(saleRecordId);
            }
            LogUtils.getInfoLog(username, "delete sale record");
            return Result.success();
        } catch (Exception e) {
            LogUtils.getErrorLog(username, "delete sale record", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    private String calculateChainIndex(List<SaleRecord> oldSaleRecord, List<SaleRecord> newSaleRecord) {
        double oldPriceSum = 0;
        double oldNumSum = 0;
        double newPriceSum = 0;
        double newNumSum = 0;
        for (SaleRecord saleRecord : oldSaleRecord) {
            oldPriceSum += saleRecord.getProductZhongleiSalePrice();
            oldNumSum += saleRecord.getProductZhongleiSaleNumber();
        }
        for (SaleRecord saleRecord : newSaleRecord) {
            newPriceSum += saleRecord.getProductZhongleiSalePrice();
            newNumSum += saleRecord.getProductZhongleiSaleNumber();
        }
        double chainIndex = newPriceSum * oldNumSum * 100 / oldPriceSum * newNumSum;
        return String.valueOf(chainIndex);
    }

    private String calculateChainIndexByPeriod(String lastPeriod, String newPeriod) {
        List<SaleRecord> oldSaleRecord = saleRecordService.findByPeriod(Integer.parseInt(lastPeriod));
        List<SaleRecord> newSaleRecord = saleRecordService.findByPeriod(Integer.parseInt(newPeriod));
        return calculateChainIndex(oldSaleRecord, newSaleRecord);
    }

    private String calculateChainIndexByPeriodAndBrandAndRegion(String lastPeriod, String newPeriod, String brand, String region) {
        List<SaleRecord> oldSaleRecord = saleRecordService.findByPeriodAndBrandAndRegion(Integer.parseInt(lastPeriod), brand, region);
        List<SaleRecord> newSaleRecord = saleRecordService.findByPeriodAndBrandAndRegion(Integer.parseInt(newPeriod), brand, region);
        return calculateChainIndex(oldSaleRecord, newSaleRecord);
    }

    private String calculateChainIndexByPeriodAndProductDaleiName(String lastPeriod, String newPeriod, String productDaleiName) {
        List<SaleRecord> oldSaleRecord = saleRecordService.findByPeriodAndProductDaleiName(Integer.parseInt(lastPeriod), productDaleiName);
        List<SaleRecord> newSaleRecord = saleRecordService.findByPeriodAndProductDaleiName(Integer.parseInt(newPeriod), productDaleiName);
        return calculateChainIndex(oldSaleRecord, newSaleRecord);
    }
}

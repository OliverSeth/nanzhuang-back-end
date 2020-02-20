package com.example.demo.controller;

import com.example.demo.domain.SaleRecord;
import com.example.demo.service.SaleRecordService;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    SaleRecordService saleRecordService;

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
            Page<SaleRecord> page = saleRecordService.findAll(pageNum, pageSize);
            LogUtils.getInfoLog("", "get all sale record");
            return Result.success(page);
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
            @ApiImplicitParam(paramType = "query", name = "startTime", value = "开始时间", dataType = "long"),
            @ApiImplicitParam(paramType = "query", name = "endTime", value = "结束时间", dataType = "long"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int"),
    })
    public Result findSaleRecordByQuerys(String merchantName, String businessCode, String recodeUsername,
                                         String uniqueCode, String productDaleiName, String productZhongleiName,
                                         Long startTime, Long endTime, Integer pageNum, Integer pageSize) {
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
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "recordTimeStamp");
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
}

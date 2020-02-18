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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

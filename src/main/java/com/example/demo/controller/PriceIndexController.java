package com.example.demo.controller;

import com.example.demo.domain.PriceIndex;
import com.example.demo.service.PriceIndexService;
import com.example.demo.utils.ConstantCode;
import com.example.demo.utils.LogUtils;
import com.example.demo.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by Oliver Seth on 2020/3/5 19:03
 */
@RestController
@RequestMapping("price-index")
public class PriceIndexController {
    @Autowired
    PriceIndexService priceIndexService;

    @GetMapping("all")
    @ApiOperation("分页获取所有价格指数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int"),
    })
    public Result findAllPriceIndex(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        try {
            List<PriceIndex> list = priceIndexService.findAll(pageNum, pageSize);
            Long length = priceIndexService.countAll();
            LogUtils.getInfoLog("", "get all price index");
            Map<String, Object> map = new HashMap<>();
            map.put("list", list);
            map.put("length", length);
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("get all price index", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }

    @GetMapping("query")
    @ApiOperation("按条件查询价格指数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "code", value = "编码"),
            @ApiImplicitParam(paramType = "query", name = "brand", value = "品牌"),
            @ApiImplicitParam(paramType = "query", name = "region", value = "地区"),
            @ApiImplicitParam(paramType = "query", name = "periodYear", value = "年"),
            @ApiImplicitParam(paramType = "query", name = "periodMonth", value = "月"),
            @ApiImplicitParam(paramType = "query", name = "periodDays", value = "旬"),
            @ApiImplicitParam(paramType = "query", name = "daleiName", value = "产品大类名"),
            @ApiImplicitParam(paramType = "query", name = "zhongleiName", value = "产品中类名"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataType = "int"),
    })
    public Result findPriceIndexByQuerys(String code, String brand, String region, String periodYear, String periodMonth,
                                         String periodDays, String daleiName, String zhongleiName, Integer pageNum, Integer pageSize) {
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
        code = code.trim().isEmpty() ? null : code;
        brand = brand.trim().isEmpty() ? null : brand;
        region = region.trim().isEmpty() ? null : region;
        periodYear = periodYear.trim().isEmpty() ? null : periodYear;
        periodMonth = periodMonth.trim().isEmpty() ? null : periodMonth;
        periodDays = periodDays.trim().isEmpty() ? null : periodDays;
        daleiName = daleiName.trim().isEmpty() ? null : daleiName;
        zhongleiName = zhongleiName.trim().isEmpty() ? null : zhongleiName;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "indexId");
        try {
            List<PriceIndex> list = priceIndexService.findPriceIndexByQuerys(code, brand, region, periodYear, periodMonth,
                    periodDays, daleiName, zhongleiName, pageable);
            Map<String, List<PriceIndex>> map = new HashMap<>();
            map.put("list", list);
            LogUtils.getInfoLog("", "find price index by query");
            return Result.success(map);
        } catch (Exception e) {
            LogUtils.getErrorLog("find price index by query", e);
            return Result.exception(ConstantCode.BASEEXCEPTION_CODE, e.toString());
        }
    }
}

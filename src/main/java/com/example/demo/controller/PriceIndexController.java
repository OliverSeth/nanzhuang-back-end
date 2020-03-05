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
}

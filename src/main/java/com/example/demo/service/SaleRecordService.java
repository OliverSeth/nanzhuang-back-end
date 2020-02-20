package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.SaleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/18 16:43
 */
public interface SaleRecordService {
    List<SaleRecord> findAll(Integer page, Integer size);

    List<SaleRecord> findSaleRecordByQuerys(String merchantName, String businessCode, String recodeUsername,
                                            String uniqueCode, String productDaleiName, String productZhongleiName,
                                            Long startTime, Long endTime, Pageable pageable);

    Long countAll();
}

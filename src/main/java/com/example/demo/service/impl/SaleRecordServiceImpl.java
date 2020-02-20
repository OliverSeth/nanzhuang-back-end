package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.domain.SaleRecord;
import com.example.demo.repository.SaleRecordRepository;
import com.example.demo.service.SaleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/18 16:45
 */
@Service
public class SaleRecordServiceImpl implements SaleRecordService {
    @Autowired
    SaleRecordRepository saleRecordRepository;

    @Override
    public Page<SaleRecord> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "saleRecordId");
        return saleRecordRepository.findAll(pageable);
    }

    @Override
    public List<SaleRecord> findSaleRecordByQuerys(String merchantName, String businessCode, String recodeUsername,
                                                   String uniqueCode, String productDaleiName, String productZhongleiName,
                                                   Long startTime, Long endTime, Integer startIndex, Integer endIndex,
                                                   Pageable pageable) {
        return saleRecordRepository.findSaleRecordByQuerys(merchantName, businessCode, recodeUsername, uniqueCode,
                productDaleiName, productZhongleiName, startTime, endTime, startIndex, endIndex, pageable);
    }
}

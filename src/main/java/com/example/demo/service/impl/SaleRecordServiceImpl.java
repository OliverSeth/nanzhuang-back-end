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
    public List<SaleRecord> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "saleRecordId");
        return saleRecordRepository.findAll(pageable).getContent();
    }

    @Override
    public List<SaleRecord> findSaleRecordByQuerys(String merchantName, String businessCode, String recodeUsername,
                                                   String uniqueCode, String productDaleiName, String productZhongleiName,
                                                   Integer startTime, Integer endTime, Pageable pageable) {
        return saleRecordRepository.findSaleRecordByQuerys(merchantName, businessCode, recodeUsername, uniqueCode,
                productDaleiName, productZhongleiName, startTime, endTime, pageable);
    }

    @Override
    public Long countAll() {
        return saleRecordRepository.count();
    }

    @Override
    public SaleRecord save(SaleRecord saleRecord) {
        return saleRecordRepository.save(saleRecord);
    }

    @Override
    public SaleRecord findBySaleRecordId(Integer saleRecordId) {
        return saleRecordRepository.findBySaleRecordId(saleRecordId);
    }

    @Override
    public void deleteBySaleRecordId(Integer saleRecordId) {
        saleRecordRepository.deleteBySaleRecordId(saleRecordId);
    }

    @Override
    public List<SaleRecord> findByPeriod(Integer period) {
        return saleRecordRepository.findByPeriod(period);
    }

    @Override
    public List<SaleRecord> findByPeriodAndBrandAndRegion(Integer period, String brand, String region) {
        return saleRecordRepository.findByPeriodAndBrandAndRegion(period, brand, region);
    }

    @Override
    public List<SaleRecord> findByPeriodAndProductDaleiName(Integer period, String productDaleiName) {
        return saleRecordRepository.findByPeriodAndProductDaleiName(period, productDaleiName);
    }

    @Override
    public List<SaleRecord> findByPeriodAndProductDaleiNameAndBrandAndRegion(Integer period, String productDaleiName, String brand, String region) {
        return saleRecordRepository.findByPeriodAndProductDaleiNameAndBrandAndRegion(period, productDaleiName, brand, region);
    }

    @Override
    public List<SaleRecord> findByPeriodAndProductZhongleiName(Integer period, String productZhongleiName) {
        return saleRecordRepository.findByPeriodAndProductZhongleiName(period, productZhongleiName);
    }

    @Override
    public List<SaleRecord> findByPeriodAndProductZhongleiNameAndBrandAndRegion(Integer period, String productZhongleiName, String brand, String region) {
        return saleRecordRepository.findByPeriodAndProductZhongleiNameAndBrandAndRegion(period, productZhongleiName, brand, region);
    }
}

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
                                            Integer startTime, Integer endTime, Pageable pageable);

    Long countAll();

    SaleRecord save(SaleRecord saleRecord);

    SaleRecord findBySaleRecordId(Integer saleRecordId);

    void deleteBySaleRecordId(Integer saleRecordId);

    List<SaleRecord> findByPeriod(Integer period);

    List<SaleRecord> findByPeriodAndBrandAndRegion(Integer period, String brand, String region);

    List<SaleRecord> findByPeriodAndProductDaleiName(Integer period, String productDaleiName);

    List<SaleRecord> findByPeriodAndProductDaleiNameAndBrandAndRegion(Integer period, String productDaleiName, String brand, String region);

    List<SaleRecord> findByPeriodAndProductZhongleiName(Integer period, String productZhongleiName);

    List<SaleRecord> findByPeriodAndProductZhongleiNameAndBrandAndRegion(Integer period, String productZhongleiName, String brand, String region);
}

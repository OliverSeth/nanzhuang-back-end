package com.example.demo.service;

import com.example.demo.domain.PriceIndex;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/3/4 19:57
 */
public interface PriceIndexService {
    List<PriceIndex> findPriceIndexByQuerys(String code, String brand, String region, String periodYear, String periodMonth,
                                            String periodDays, String daleiName, String zhongleiName, Pageable pageable);

    Long countAll();

    List<PriceIndex> findAll(Integer page, Integer size);

    PriceIndex save(PriceIndex priceIndex);

    PriceIndex findByPeriodAndProductDaleiNameAndProductZhongleiNameAndBrandAndRegion(Integer period, String productDaleiName,
                                                                                      String productZhongleiName, String brand, String region);

    List<PriceIndex> findAllByPeriod(Integer period);

    Long countByQuerys(String code, String brand, String region, String periodYear, String periodMonth,
                       String periodDays, String daleiName, String zhongleiName);

    void deleteAllByPeriod(Integer period);
}

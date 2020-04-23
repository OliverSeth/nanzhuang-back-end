package com.example.demo.service.impl;

import com.example.demo.domain.PriceIndex;
import com.example.demo.repository.PriceIndexRepository;
import com.example.demo.service.PriceIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/3/4 20:01
 */
@Service
public class PriceIndexServiceImpl implements PriceIndexService {
    @Autowired
    PriceIndexRepository priceIndexRepository;

    @Override
    public List<PriceIndex> findPriceIndexByQuerys(String code, String brand, String region, String periodYear, String periodMonth,
                                                   String periodDays, String daleiName, String zhongleiName, Pageable pageable) {
        return priceIndexRepository.findPriceIndexByQuerys(code, brand, region, periodYear, periodMonth, periodDays,
                daleiName, zhongleiName, pageable);
    }

    @Override
    public Long countAll() {
        return priceIndexRepository.count();
    }

    @Override
    public List<PriceIndex> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "period");
        return priceIndexRepository.findAll(pageable).getContent();
    }

    @Override
    public PriceIndex save(PriceIndex priceIndex) {
        return priceIndexRepository.save(priceIndex);
    }

    @Override
    public PriceIndex findByPeriodAndProductDaleiNameAndProductZhongleiNameAndBrandAndRegion(Integer period, String productDaleiName, String productZhongleiName, String brand, String region) {
        return priceIndexRepository.findByPeriodAndProductDaleiNameAndProductZhongleiNameAndBrandAndRegion(period, productDaleiName, productZhongleiName, brand, region);
    }

    @Override
    public List<PriceIndex> findAllByPeriod(Integer period) {
        return priceIndexRepository.findAllByPeriod(period);
    }

    @Override
    public Long countByQuerys(String code, String brand, String region, String periodYear, String periodMonth, String periodDays, String daleiName, String zhongleiName) {
        return priceIndexRepository.countByQuerys(code, brand, region, periodYear, periodMonth, periodDays, daleiName, zhongleiName);
    }
}

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
    public List<PriceIndex> findPriceIndexByQuerys(String code, String brand, String region, Pageable pageable) {
        return priceIndexRepository.findPriceIndexByQuerys(code, brand, region, pageable);
    }

    @Override
    public Long countAll() {
        return priceIndexRepository.count();
    }

    @Override
    public List<PriceIndex> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "indexId");
        return priceIndexRepository.findAll(pageable).getContent();
    }
}
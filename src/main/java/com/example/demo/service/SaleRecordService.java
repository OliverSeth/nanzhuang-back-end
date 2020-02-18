package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.SaleRecord;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/18 16:43
 */
public interface SaleRecordService {
    Page<SaleRecord> findAll(Integer page, Integer size);
}

package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/17 16:46
 */
public interface ProductService {
    Product findByProductId(Integer productId);

    Product findByProductCode(String productCode);

    Product findByProductName(String productName);

    List<Product> findAllByTypeLevel(String typeLevel);

    List<Product> findAll();

    Product save(Product product);
}

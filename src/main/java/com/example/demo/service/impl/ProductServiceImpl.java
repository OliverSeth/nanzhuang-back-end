package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/17 16:50
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findByProductId(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findByProductCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByTypeLevel(String typeLevel) {
        return productRepository.findAllByTypeLevel(typeLevel);
    }

    @Override
    public void deleteByProductId(Integer productId) {
        productRepository.deleteByProductId(productId);
    }
}

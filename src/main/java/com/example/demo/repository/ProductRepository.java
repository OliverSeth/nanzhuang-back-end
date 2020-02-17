package com.example.demo.repository;

import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/17 16:45
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByProductId(Integer productId);

    Product findByProductCode(String productCode);

    Product findByProductName(String productName);

    List<Product> findAllByTypeLevel(String typeLevel);
}

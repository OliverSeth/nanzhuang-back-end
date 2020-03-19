package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by Oliver Seth on 2020/2/17 15:54
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(unique = true)
    private String productCode;

    @Column(unique = true)
    private String productName;

    private String typeLevel;

    private Integer daleiId;

    public Product() {
    }

    public Product(String productCode, String productName, String typeLevel) {
        this.productCode = productCode;
        this.productName = productName;
        this.typeLevel = typeLevel;
    }

    public Product(String productCode, String productName, String typeLevel, Integer daleiId) {
        this.productCode = productCode;
        this.productName = productName;
        this.typeLevel = typeLevel;
        this.daleiId = daleiId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(String typeLevel) {
        this.typeLevel = typeLevel;
    }

    public Integer getDaleiId() {
        return daleiId;
    }

    public void setDaleiId(Integer daleiId) {
        this.daleiId = daleiId;
    }
}

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

    private String type;

    private String typeLevel;

    public Product() {
    }

    public Product(String productCode, String productName, String type, String typeLevel) {
        this.productCode = productCode;
        this.productName = productName;
        this.type = type;
        this.typeLevel = typeLevel;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(String typeLevel) {
        this.typeLevel = typeLevel;
    }
}

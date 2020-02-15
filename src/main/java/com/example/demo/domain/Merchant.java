package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by Oliver Seth on 2020/2/2 18:58
 */
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantId;

    @Column(unique = true)
    private String username;

    private String ownerName;

    @Column(unique = true)
    private String businessCode;

    @Column(unique = true)
    private String uniqueCode;

    public Merchant() {
    }

    public Merchant(String username, String ownerName, String businessCode, String uniqueCode) {
        this.username = username;
        this.ownerName = ownerName;
        this.businessCode = businessCode;
        this.uniqueCode = uniqueCode;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}

package com.example.demo.dto;

/**
 * Created by Oliver Seth on 2020/2/21 17:05
 */
public class MerchantDTO {
    private Integer merchantId;
    private String ownerName;
    private String businessCode;
    private String uniqueCode;

    public MerchantDTO() {
    }

    public MerchantDTO(Integer merchantId, String ownerName, String businessCode, String uniqueCode) {
        this.merchantId = merchantId;
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

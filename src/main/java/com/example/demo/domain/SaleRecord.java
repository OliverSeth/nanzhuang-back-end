package com.example.demo.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Oliver Seth on 2020/2/18 15:20
 */
@Entity
@Table(name = "sale_record")
public class SaleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleRecordId;
    private Integer recodeUsername;
    private String periodYear;         //期数
    private String periodMonth;
    private String periodDays;
    private String brand;          //品牌
    private String region;         //区域
    private String merchantName;
    private String businessCode;
    private String uniqueCode;
    private String productDaleiCode;
    private String productDaleiName;
    private double productDaleiSales;  //大类销售额
    private String productZhongleiCode;
    private String productZhongleiName;
    private double productZhongleiSalePrice;
    private Integer productZhongleiSaleNumber;
    private String representationNumber;
    private double currentPrice;
    private Integer currentSales;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String note;
    private Long recordTimeStamp;

    public SaleRecord() {
    }

    public SaleRecord(Integer recodeUsername, String periodYear, String periodMonth, String periodDays, String brand, String region, String merchantName, String businessCode, String uniqueCode, String productDaleiCode, String productDaleiName, double productDaleiSales, String productZhongleiCode, String productZhongleiName, double productZhongleiSalePrice, Integer productZhongleiSaleNumber, String representationNumber, double currentPrice, Integer currentSales, String a, String b, String c, String d, String e, String f, String note, Long recordTimeStamp) {
        this.recodeUsername = recodeUsername;
        this.periodYear = periodYear;
        this.periodMonth = periodMonth;
        this.periodDays = periodDays;
        this.brand = brand;
        this.region = region;
        this.merchantName = merchantName;
        this.businessCode = businessCode;
        this.uniqueCode = uniqueCode;
        this.productDaleiCode = productDaleiCode;
        this.productDaleiName = productDaleiName;
        this.productDaleiSales = productDaleiSales;
        this.productZhongleiCode = productZhongleiCode;
        this.productZhongleiName = productZhongleiName;
        this.productZhongleiSalePrice = productZhongleiSalePrice;
        this.productZhongleiSaleNumber = productZhongleiSaleNumber;
        this.representationNumber = representationNumber;
        this.currentPrice = currentPrice;
        this.currentSales = currentSales;
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        this.note = note;
        this.recordTimeStamp = recordTimeStamp;
    }

    public Integer getSaleRecordId() {
        return saleRecordId;
    }

    public void setSaleRecordId(Integer saleRecordId) {
        this.saleRecordId = saleRecordId;
    }

    public Integer getRecodeUsername() {
        return recodeUsername;
    }

    public void setRecodeUsername(Integer recodeUsername) {
        this.recodeUsername = recodeUsername;
    }

    public String getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(String periodYear) {
        this.periodYear = periodYear;
    }

    public String getPeriodMonth() {
        return periodMonth;
    }

    public void setPeriodMonth(String periodMonth) {
        this.periodMonth = periodMonth;
    }

    public String getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(String periodDays) {
        this.periodDays = periodDays;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getProductDaleiCode() {
        return productDaleiCode;
    }

    public void setProductDaleiCode(String productDaleiCode) {
        this.productDaleiCode = productDaleiCode;
    }

    public String getProductDaleiName() {
        return productDaleiName;
    }

    public void setProductDaleiName(String productDaleiName) {
        this.productDaleiName = productDaleiName;
    }

    public double getProductDaleiSales() {
        return productDaleiSales;
    }

    public void setProductDaleiSales(double productDaleiSales) {
        this.productDaleiSales = productDaleiSales;
    }

    public String getProductZhongleiCode() {
        return productZhongleiCode;
    }

    public void setProductZhongleiCode(String productZhongleiCode) {
        this.productZhongleiCode = productZhongleiCode;
    }

    public String getProductZhongleiName() {
        return productZhongleiName;
    }

    public void setProductZhongleiName(String productZhongleiName) {
        this.productZhongleiName = productZhongleiName;
    }

    public double getProductZhongleiSalePrice() {
        return productZhongleiSalePrice;
    }

    public void setProductZhongleiSalePrice(double productZhongleiSalePrice) {
        this.productZhongleiSalePrice = productZhongleiSalePrice;
    }

    public Integer getProductZhongleiSaleNumber() {
        return productZhongleiSaleNumber;
    }

    public void setProductZhongleiSaleNumber(Integer productZhongleiSaleNumber) {
        this.productZhongleiSaleNumber = productZhongleiSaleNumber;
    }

    public String getRepresentationNumber() {
        return representationNumber;
    }

    public void setRepresentationNumber(String representationNumber) {
        this.representationNumber = representationNumber;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getCurrentSales() {
        return currentSales;
    }

    public void setCurrentSales(Integer currentSales) {
        this.currentSales = currentSales;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getRecordTimeStamp() {
        return recordTimeStamp;
    }

    public void setRecordTimeStamp(Long recordTimeStamp) {
        this.recordTimeStamp = recordTimeStamp;
    }
}

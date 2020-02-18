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
    private String perodDays;
    private String brand;          //品牌
    private String region;         //区域
    private Integer merchantId;
    private String productDaleiCode;
    private String productDaleiName;
    private Integer productDaleiSales;  //大类销售额
    private String productZhongleiCode;
    private String productZhongleiName;
    private Integer productZhongleiSales;
    private String representationNumber;
    private Integer currentPrice;
    private Integer currentSales;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String note;
    private Date date;

    public SaleRecord() {
    }

    public SaleRecord(Integer recodeUsername, String periodYear, String periodMonth, String perodDays, String brand, String region, Integer merchantId, String productDaleiCode, String productDaleiName, Integer productDaleiSales, String productZhongleiCode, String productZhongleiName, Integer productZhongleiSales, String representationNumber, Integer currentPrice, Integer currentSales, String a, String b, String c, String d, String e, String f, String note, Date date) {
        this.recodeUsername = recodeUsername;
        this.periodYear = periodYear;
        this.periodMonth = periodMonth;
        this.perodDays = perodDays;
        this.brand = brand;
        this.region = region;
        this.merchantId = merchantId;
        this.productDaleiCode = productDaleiCode;
        this.productDaleiName = productDaleiName;
        this.productDaleiSales = productDaleiSales;
        this.productZhongleiCode = productZhongleiCode;
        this.productZhongleiName = productZhongleiName;
        this.productZhongleiSales = productZhongleiSales;
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
        this.date = date;
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

    public String getPerodDays() {
        return perodDays;
    }

    public void setPerodDays(String perodDays) {
        this.perodDays = perodDays;
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

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    public Integer getProductDaleiSales() {
        return productDaleiSales;
    }

    public void setProductDaleiSales(Integer productDaleiSales) {
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

    public Integer getProductZhongleiSales() {
        return productZhongleiSales;
    }

    public void setProductZhongleiSales(Integer productZhongleiSales) {
        this.productZhongleiSales = productZhongleiSales;
    }

    public String getRepresentationNumber() {
        return representationNumber;
    }

    public void setRepresentationNumber(String representationNumber) {
        this.representationNumber = representationNumber;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by Oliver Seth on 2020/2/22 16:35
 */
@Entity
@Table(name = "price_index")
public class PriceIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer indexId;
    private String periodYear;
    private String periodMonth;
    private String periodDays;
    private Integer period;
    private String code;
    private String brand;
    private String region;
    private Integer daleiId;
    private Integer zhongleiId;
    private String fixedBaseIndex;          //定基指数
    private String fixedBaseAdjustIndex;
    private String yearOnYearIndex;         //同比指数
    private String yearOnYearAdjustIndex;
    private String chainIndex;              //环比指数
    private String chainAdjustIndex;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getDaleiId() {
        return daleiId;
    }

    public void setDaleiId(Integer daleiId) {
        this.daleiId = daleiId;
    }

    public Integer getZhongleiId() {
        return zhongleiId;
    }

    public void setZhongleiId(Integer zhongleiId) {
        this.zhongleiId = zhongleiId;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getFixedBaseIndex() {
        return fixedBaseIndex;
    }

    public void setFixedBaseIndex(String fixedBaseIndex) {
        this.fixedBaseIndex = fixedBaseIndex;
    }

    public String getFixedBaseAdjustIndex() {
        return fixedBaseAdjustIndex;
    }

    public void setFixedBaseAdjustIndex(String fixedBaseAdjustIndex) {
        this.fixedBaseAdjustIndex = fixedBaseAdjustIndex;
    }

    public String getYearOnYearIndex() {
        return yearOnYearIndex;
    }

    public void setYearOnYearIndex(String yearOnYearIndex) {
        this.yearOnYearIndex = yearOnYearIndex;
    }

    public String getYearOnYearAdjustIndex() {
        return yearOnYearAdjustIndex;
    }

    public void setYearOnYearAdjustIndex(String yearOnYearAdjustIndex) {
        this.yearOnYearAdjustIndex = yearOnYearAdjustIndex;
    }

    public String getChainIndex() {
        return chainIndex;
    }

    public void setChainIndex(String chainIndex) {
        this.chainIndex = chainIndex;
    }

    public String getChainAdjustIndex() {
        return chainAdjustIndex;
    }

    public void setChainAdjustIndex(String chainAdjustIndex) {
        this.chainAdjustIndex = chainAdjustIndex;
    }
}

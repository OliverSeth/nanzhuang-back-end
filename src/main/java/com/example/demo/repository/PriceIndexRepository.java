package com.example.demo.repository;

import com.example.demo.domain.PriceIndex;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/3/4 19:49
 */
public interface PriceIndexRepository extends JpaRepository<PriceIndex, Integer> {
    @Query(value = "select p from PriceIndex p " +
            "where (p.code = :code or :code is null ) " +
            "and (p.brand = :brand or :brand is null ) " +
            "and (p.region = :region or :region is null ) " +
            "and (p.periodYear = :periodYear or :periodYear is null ) " +
            "and (p.periodMonth = :periodMonth or :periodMonth is null ) " +
            "and (p.periodDays = :periodDays or :periodDays is null ) " +
            "and (p.productDaleiName = :productDaleiName or :productDaleiName is null ) " +
            "and (p.productZhongleiName = :productZhongleiName or :productZhongleiName is null )")
    List<PriceIndex> findPriceIndexByQuerys(@Param("code") String code,
                                            @Param("brand") String brand,
                                            @Param("region") String region,
                                            @Param("periodYear") String periodYear,
                                            @Param("periodMonth") String periodMonth,
                                            @Param("periodDays") String periodDays,
                                            @Param("productDaleiName") String productDaleiName,
                                            @Param("productZhongleiName") String productZhongleiName,
                                            Pageable pageable);

    PriceIndex findByPeriodAndProductDaleiNameAndProductZhongleiNameAndBrandAndRegion(Integer period, String productDaleiName,
                                                                                      String productZhongleiName, String brand, String region);

    List<PriceIndex> findAllByPeriod(Integer period);
}

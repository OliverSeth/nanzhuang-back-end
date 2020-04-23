package com.example.demo.repository;

import com.example.demo.domain.SaleRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/18 15:57
 */
public interface SaleRecordRepository extends JpaRepository<SaleRecord, Integer> {
    @Query(value = "select s from SaleRecord s " +
            "where (s.merchantName = :merchantName or :merchantName is null )" +
            "and (s.businessCode = :businessCode or :businessCode is null )" +
            "and (s.recodeUsername = :recodeUsername or :recodeUsername is null )" +
            "and (s.uniqueCode = :uniqueCode or :uniqueCode is null )" +
            "and (s.productDaleiName = :productDaleiName or :productDaleiName is null )" +
            "and (s.productZhongleiName = :productZhongleiName or :productZhongleiName is null )" +
            "and ((s.period between :startTime and :endTime ) or (:startTime is null ) or (:endTime is null ))")
    List<SaleRecord> findSaleRecordByQuerys(@Param("merchantName") String merchantName,
                                            @Param("businessCode") String businessCode,
                                            @Param("recodeUsername") String recodeUsername,
                                            @Param("uniqueCode") String uniqueCode,
                                            @Param("productDaleiName") String productDaleiName,
                                            @Param("productZhongleiName") String productZhongleiName,
                                            @Param("startTime") Integer startTime,
                                            @Param("endTime") Integer endTime,
                                            Pageable pageable);

    List<SaleRecord> findByPeriod(Integer period);

    List<SaleRecord> findByPeriodAndBrandAndRegion(Integer period, String brand, String region);

    List<SaleRecord> findByPeriodAndProductDaleiName(Integer period, String productDaleiName);

    List<SaleRecord> findByPeriodAndProductDaleiNameAndBrandAndRegion(Integer period, String productDaleiName, String brand, String region);

    List<SaleRecord> findByPeriodAndProductZhongleiName(Integer period, String productZhongleiName);

    List<SaleRecord> findByPeriodAndProductZhongleiNameAndBrandAndRegion(Integer period, String productZhongleiName, String brand, String region);

    SaleRecord findBySaleRecordId(Integer saleRecordId);

    @Modifying
    @Transactional
    void deleteBySaleRecordId(Integer saleRecordId);

    @Query(value = "select count(s) from SaleRecord s " +
            "where (s.merchantName = :merchantName or :merchantName is null )" +
            "and (s.businessCode = :businessCode or :businessCode is null )" +
            "and (s.recodeUsername = :recodeUsername or :recodeUsername is null )" +
            "and (s.uniqueCode = :uniqueCode or :uniqueCode is null )" +
            "and (s.productDaleiName = :productDaleiName or :productDaleiName is null )" +
            "and (s.productZhongleiName = :productZhongleiName or :productZhongleiName is null )" +
            "and ((s.period between :startTime and :endTime ) or (:startTime is null ) or (:endTime is null ))")
    Long countByQuerys(@Param("merchantName") String merchantName,
                       @Param("businessCode") String businessCode,
                       @Param("recodeUsername") String recodeUsername,
                       @Param("uniqueCode") String uniqueCode,
                       @Param("productDaleiName") String productDaleiName,
                       @Param("productZhongleiName") String productZhongleiName,
                       @Param("startTime") Integer startTime,
                       @Param("endTime") Integer endTime);
}

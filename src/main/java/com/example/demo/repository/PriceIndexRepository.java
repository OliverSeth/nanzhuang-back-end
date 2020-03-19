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
            "and (p.daleiId = :daleiId or :daleiId is null ) " +
            "and (p.zhongleiId = :zhongleiId or :zhongleiId is null )")
    List<PriceIndex> findPriceIndexByQuerys(@Param("code") String code,
                                            @Param("brand") String brand,
                                            @Param("region") String region,
                                            @Param("daleiId") Integer daleiId,
                                            @Param("zhongleiId") Integer zhongleiId,
                                            Pageable pageable);
}

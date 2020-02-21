package com.example.demo.repository;

import com.example.demo.domain.Merchant;
import com.example.demo.dto.MerchantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/15 15:44
 */
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    Merchant findByUsername(String username);

    Merchant findByBusinessCode(String businessCode);

    Merchant findByUniqueCode(String uniqueCode);

    Merchant findByMerchantId(Integer merchantId);

    void deleteByMerchantId(Integer merchantId);

    @Query(value = "select new com.example.demo.dto.MerchantDTO(m.merchantId,m.ownerName,m.businessCode,m.uniqueCode) from Merchant m")
    List<MerchantDTO> findAllMerchantDTO();
}

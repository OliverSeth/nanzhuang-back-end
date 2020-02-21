package com.example.demo.service;

import com.example.demo.domain.Merchant;
import com.example.demo.dto.MerchantDTO;

import java.util.List;

/**
 * Created by Oliver Seth on 2020/2/15 15:47
 */
public interface MerchantService {
    Merchant findByUsername(String username);

    Merchant findByBusinessCode(String businessCode);

    Merchant findByUniqueCode(String uniqueCode);

    Merchant findByMerchantId(Integer merchantId);

    Merchant save(Merchant merchant);

    void deleteByMerchantId(Integer merchantId);

    List<MerchantDTO> findAllMerchantDTO();
}

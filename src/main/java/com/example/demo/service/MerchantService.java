package com.example.demo.service;

import com.example.demo.domain.Merchant;

/**
 * Created by Oliver Seth on 2020/2/15 15:47
 */
public interface MerchantService {
    Merchant findByUsername(String username);

    Merchant findByBusinessCode(String businessCode);

    Merchant findByUniqueCode(String uniqueCode);

    Merchant findByMerchantId(Integer merchantId);

    void save(Merchant merchant);
}

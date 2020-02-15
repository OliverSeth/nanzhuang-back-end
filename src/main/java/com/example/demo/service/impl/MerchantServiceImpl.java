package com.example.demo.service.impl;

import com.example.demo.domain.Merchant;
import com.example.demo.repository.MerchantRepository;
import com.example.demo.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oliver Seth on 2020/2/15 15:50
 */
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant findByUsername(String username) {
        return merchantRepository.findByUsername(username);
    }

    @Override
    public void save(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    @Override
    public Merchant findByMerchantId(Integer merchantId) {
        return merchantRepository.findByMerchantId(merchantId);
    }

    @Override
    public Merchant findByBusinessCode(String businessCode) {
        return merchantRepository.findByBusinessCode(businessCode);
    }

    @Override
    public Merchant findByUniqueCode(String uniqueCode) {
        return merchantRepository.findByUniqueCode(uniqueCode);
    }
}

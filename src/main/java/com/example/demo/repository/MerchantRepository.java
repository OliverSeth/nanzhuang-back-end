package com.example.demo.repository;

import com.example.demo.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Oliver Seth on 2020/2/15 15:44
 */
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
    Merchant findByUsername(String username);

    Merchant findByBusinessCode(String businessCode);

    Merchant findByUniqueCode(String uniqueCode);
}

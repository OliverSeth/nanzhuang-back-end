package com.example.demo.repository;

import com.example.demo.domain.SaleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Oliver Seth on 2020/2/18 15:57
 */
public interface SaleRecordRepository extends JpaRepository<SaleRecord, Integer> {
}

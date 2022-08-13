package com.example.demo.repository;

import com.example.demo.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface WalletRepository extends JpaRepository<Wallet, Long> {

    /**
     * @param accountId Long
     */
    Wallet findFirstByAccountId(Long accountId);
}
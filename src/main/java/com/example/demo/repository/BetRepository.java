package com.example.demo.repository;

import com.example.demo.dto.BetData;
import com.example.demo.dto.BetStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BetRepository extends JpaRepository<BetData, Long> {

    /**
     * Updates all pending bets with a given Bet Status
     *
     * @param betStatusType String
     */
    @Modifying
    @Transactional
    @Query("UPDATE bets b SET b.status = :betStatusType WHERE b.status = 'pending'")
    Integer updateAllPendingBets(@Param("betStatusType") String betStatusType);
}
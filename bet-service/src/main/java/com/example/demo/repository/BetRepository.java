package com.example.demo.repository;

import com.example.demo.dto.BetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BetRepository extends JpaRepository<BetData, Long> {

//    /**
//     * Updates all pending bets with a given Bet Status
//     *
//     * @param betStatusType String
//     */
//    @Modifying
//    @Transactional
//    @Query("UPDATE bet b SET b.status = :betStatusType WHERE b.status = 'pending'")
//    Integer updateAllPendingBets(@Param("betStatusType") String betStatusType);
    @Transactional
    @Modifying
    @Query("update BetData b set b.outcome = ?1 where b.id = ?2")
    void settleSingleBetById(String result, Long id);

    List<BetData> findByStatusContaining(String status);
}
package com.example.demo.repository;

import com.example.demo.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BetRepository extends JpaRepository<Bet, Long> {

    /**
     * Updates all bet with a given Bet Status
     *
     * @param result String
     * @param id Long
    */
    @Transactional
    @Modifying
    @Query("update Bet b set b.outcome = ?1 where b.id = ?2")
    void settleSingleBetById(String result, Long id);

    /**
     * Updates all pending bets with a given Bet Status
     *
     * @param result String
     * @param status String
     */
    @Transactional
    @Modifying
    @Query("update Bet b set b.outcome = ?1 where b.status = ?2")
    void settlePendingBets(String result, String status);

    List<Bet> findByStatusContaining(String status);
}
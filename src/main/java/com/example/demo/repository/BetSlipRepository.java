package com.example.demo.repository;

import com.example.demo.dto.BetSlip;
import com.example.demo.dto.BetSlipStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BetSlipRepository extends JpaRepository<BetSlip, Long> {

    /**
     * Retrieves all the BetSlip for a given account, identified by his account id.
     *
     * @param accountId the id of the account
     * @return a list containing all the BetSlip for the given account,
     */
    List<BetSlip> findByAccountId(final Long accountId);

    List<BetSlip> findByStatus(String status);
}
package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.rest.response.OperationResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BetService {

    BetSlip createBetSlip(BetSlipData betSlipData);

    BetSlip getBetSlip(Long id);

    List<BetSlip> allBetSlips(Long accountId);

    BetData createBet(BetData bet);

    OperationResponse settleSingleBet(Long betId, String betPositionType);

    List<BetSlip> getBetSlipsByStatus(String betStatusType);

    List<BetData> findBetsByStatus(String BetStatus);

    OperationResponse settlePendingBets(String result, String status);
}

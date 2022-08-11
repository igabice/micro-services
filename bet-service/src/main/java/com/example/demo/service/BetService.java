package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.rest.response.OperationResponse;

import java.util.List;

public interface BetService {

    BetSlip createBetSlip(BetSlipData betSlipData);

    BetSlip getBetSlip(Long id);

    List<BetSlip> allBetSlips(Long accountId);

    BetData createBet(BetData bet);

    OperationResponse settleSingleBet(Long betId, String betPositionType);

//    Integer settlePendingBets(String betPositionType);

    List<BetSlip> getBetSlipsByStatus(String betStatusType);

    List<BetData> findBetsByStatus(String BetStatus);
}

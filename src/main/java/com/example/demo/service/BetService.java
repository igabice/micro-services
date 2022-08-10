package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.rest.request.BetItem;
import com.example.demo.rest.request.NewBetSlipRequest;

import java.util.List;

public interface BetService {

    BetSlip createBetSlip(BetSlipData betSlipData, List<BetItem> betItems);

    BetSlip getBetSlip(Long id);

    List<BetSlip> allBetSlips(Long accountId);

    BetData createBet(BetData bet);

    BetData settleSingleBet(Long betId, String betPositionType);

    Integer settlePendingBets(String betPositionType);

    List<BetSlip> getBetSlipsByStatus(String betStatusType);


}

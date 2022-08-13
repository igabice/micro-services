package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Bet;
import com.example.demo.model.BetSlip;
import com.example.demo.rest.response.OperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BetService {

    BetSlip createBetSlip(BetSlipData betSlipData);

    BetSlip getBetSlip(Long id);

    List<BetSlip> allBetSlips(Long accountId);

    Bet createBet(Bet bet);

    OperationResponse settleSingleBet(Long betId, String betPositionType);

    List<BetSlip> getBetSlipsByStatus(String betStatusType);

    List<Bet> findBetsByStatus(String BetStatus);

    OperationResponse settlePendingBets(String result, String status);
}

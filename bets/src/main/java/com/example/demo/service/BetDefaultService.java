package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Bet;
import com.example.demo.model.BetItem;
import com.example.demo.model.BetSlip;
import com.example.demo.repository.BetItemRepository;
import com.example.demo.repository.BetRepository;
import com.example.demo.repository.BetSlipRepository;
import com.example.demo.rest.response.OperationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetDefaultService implements BetService{

    @Autowired
    BetSlipRepository betSlipRepository;
    @Autowired
    BetRepository betRepository;
    @Autowired
    BetItemRepository betItemRepository;

    @Override
    public BetSlip createBetSlip(BetSlipData betSlipData) {
        List<BetItem> betItemList = new ArrayList<BetItem>();

        double totalOdds = 0.0;
        for (com.example.demo.rest.request.BetItem item: betSlipData.betItems()) {
            totalOdds += item.getOdd();
        }

        BetSlip betSlip = betSlipRepository.save(new BetSlip(betSlipData.accountId(), betSlipData.stake(), totalOdds));

        for (com.example.demo.rest.request.BetItem item: betSlipData.betItems()) {
            betItemList.add( new BetItem(item.getBetId(), betSlip.getId(), item.getPosition()));
        }
        betItemRepository.saveAll(betItemList);

        return betSlip;
    }

    @Override
    public BetSlip getBetSlip(Long id) {
        return betSlipRepository.getReferenceById(id);
    }

    @Override
    public List<BetSlip> allBetSlips(Long accountId) {
        return betSlipRepository.findByAccountId(accountId);
    }

    @Override
    public Bet createBet(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public OperationResponse settleSingleBet(Long betId, String result) {

        betRepository.settleSingleBetById(result, betId);
        return new OperationResponse(betId, "UPDATE_SUCCESSFUL");
    }
    @Override
    public OperationResponse settlePendingBets(String result, String status) {
        betRepository.settlePendingBets(result, status);
        return new OperationResponse(1L, "UPDATE_SUCCESSFUL");
    }

    @Override
    public List<BetSlip> getBetSlipsByStatus(String betStatusType) {
        return betSlipRepository.findByStatus(betStatusType);
    }

    @Override
    public List<Bet> findBetsByStatus(String betStatus) {
        return betRepository.findByStatusContaining(betStatus);
    }
}

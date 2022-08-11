package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.BetItemRepository;
import com.example.demo.repository.BetRepository;
import com.example.demo.repository.BetSlipRepository;
import com.example.demo.rest.request.BetItem;
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
        List<BetItemData> betItemDataList = new ArrayList<BetItemData>();

        double totalOdds = 0.0;
        for (BetItem item: betSlipData.betItems()) {
            totalOdds += item.getOdd();
        }

        BetSlip betSlip = betSlipRepository.save(new BetSlip(betSlipData.accountId(), betSlipData.stake(), totalOdds));

        for (BetItem item: betSlipData.betItems()) {
            betItemDataList.add( new BetItemData(item.getBetId(), betSlip.getId(), item.getPosition()));
        }
        betItemRepository.saveAll(betItemDataList);

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
    public BetData createBet(BetData bet) {
        return betRepository.save(bet);
    }

    @Override
    public OperationResponse settleSingleBet(Long betId, String result) {

        betRepository.settleSingleBetById(result, betId);
        return new OperationResponse(betId, "UPDATE_SUCCESSFUL");
    }

//    @Override
//    public Integer settlePendingBets(String betPositionType) {
//        return betRepository.updateAllPendingBets(betPositionType);
//    }

    @Override
    public List<BetSlip> getBetSlipsByStatus(String betStatusType) {
        return betSlipRepository.findByStatus(betStatusType);
    }

    @Override
    public List<BetData> findBetsByStatus(String betStatus) {
        return betRepository.findByStatus(betStatus);
    }
}

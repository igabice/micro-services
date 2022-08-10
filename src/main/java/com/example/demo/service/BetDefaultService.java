package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.BetItemRepository;
import com.example.demo.repository.BetRepository;
import com.example.demo.repository.BetSlipRepository;
import com.example.demo.rest.request.BetItem;

import java.util.Collections;
import java.util.List;

public class BetDefaultService implements BetService{

    BetSlipRepository betSlipRepository;
    BetRepository betRepository;
    BetItemRepository betItemRepository;

    @Override
    public BetSlip createBetSlip(BetSlipData betSlipData, List<BetItem> betItems) {
        List<BetItemData> betItemDataList = Collections.<BetItemData>emptyList();

        double totalOdds = 0.0;
        for (BetItem item: betItems) {
            totalOdds += item.getOdd();
        }

        BetSlip betSlip = betSlipRepository.save(new BetSlip(betSlipData.accountId(), betSlipData.stake(), totalOdds));

        for (BetItem item: betItems) {
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
    public BetData settleSingleBet(Long betId, String result) {
        BetData bet = betRepository.getReferenceById(betId);
        bet.setOutcome(result);
        return betRepository.save(bet);
    }

    @Override
    public Integer settlePendingBets(String betPositionType) {
        return betRepository.updateAllPendingBets(betPositionType);
    }

    @Override
    public List<BetSlip> getBetSlipsByStatus(String betStatusType) {
        return betSlipRepository.findByStatus(betStatusType);
    }
}

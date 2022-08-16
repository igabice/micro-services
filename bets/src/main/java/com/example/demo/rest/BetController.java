package com.example.demo.rest;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.example.demo.model.Bet;
import com.example.demo.model.BetSlip;
import com.example.demo.client.WalletClient;
import com.example.demo.dto.BetSlipData;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.rest.request.*;
import com.example.demo.rest.response.BalanceResponse;
import com.example.demo.rest.response.BetSlipResponse;
import com.example.demo.rest.response.NewBetResponse;
import com.example.demo.rest.response.OperationResponse;
import com.example.demo.service.BetDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bet")
@RequiredArgsConstructor
public class BetController {

    @Autowired
    BetDefaultService service;

    @Autowired
     WalletClient walletClient;

    public static Logger logger= Logger.getLogger("global");

    @PostMapping("/all")
    public List<Bet> allBet(@RequestBody BetSlipByStatusRequest request) {
        if (request == null || request.getStatus() == null) {
            throw new InvalidRequestException("INVALID_REQUEST! Status required");
        }
        return service.findBetsByStatus(request.getStatus());
    }


    @PostMapping
    public NewBetResponse create(@RequestBody NewBetRequest request) {
        if (request == null || request.getHomeOdd() < 1  || request.getAwayOdd() < 1 || request.getDrawOdd() < 1) {
            throw new InvalidRequestException("INVALID_REQUEST! Home, away or draw odd must be greater than 1");
        }
        Bet bet = service.createBet(new Bet(request.getName(), request.getHomeOdd(), request.getAwayOdd(), request.getDrawOdd()));
        return new NewBetResponse(bet, "Created Successfully");
    }

    @PostMapping("/slip")
    public BetSlipResponse createBetSlip(@RequestBody NewBetSlipRequest request) throws RestClientException, IOException {
        if (request == null || request.getStake() < 1) {
            return new BetSlipResponse(1L, "INVALID_STAKE");
        }
        if (request.getBetItems().isEmpty()) {
            return new BetSlipResponse(1L, "INVALID_BET");
        }
        BalanceResponse balance = walletClient.getBalance(request.getAccountId());
        if(balance.amount() < request.getStake()){
            return new BetSlipResponse(1L, "BET_PLACED");
        }
        BetSlip betSlip = service.createBetSlip(new BetSlipData(request.getAccountId(), request.getStake(), request.getBetItems()));
        return new BetSlipResponse(betSlip.getId(), "BET_PLACED");
    }

    @GetMapping("/slip/{id}")
    public BetSlipResponse getBetSlip(@PathVariable("id") Long id) {
        if (id == null) {
            throw new InvalidRequestException("INVALID_ID");
        }
        BetSlip betSlip = service.getBetSlip(id);
        return new BetSlipResponse(betSlip.getId(), "BETSLIP_FOUND");
    }

    @GetMapping("/slip/account/{accountId}")
    public List<BetSlip> allBetSlips(@PathVariable("accountId") Long accountId) {
        if (accountId == null) {
            throw new InvalidRequestException("INVALID_ID");
        }
        return service.allBetSlips(accountId);
    }

    @PostMapping("/slip/by/status")
    public List<BetSlip> allBetSlips(@RequestBody BetSlipByStatusRequest request) {
        if (request == null || request.getStatus() == null) {
            throw new InvalidRequestException("INVALID_REQUEST");
        }
        return service.getBetSlipsByStatus(request.getStatus());
    }

    @PostMapping("/settle-single-bet")
    public OperationResponse settleSingleBet(@RequestBody SettleSingleBetRequest request) {
        if (request == null || request.getResult() == null) {
            throw new InvalidRequestException("INVALID_REQUEST");
        }
        return service.settleSingleBet(request.getBetId(), request.getResult());
    }
    @PostMapping("/settle-pending-bets")
    public OperationResponse settlePendingBets(@RequestBody SettlePendingBetsRequest request) {
        logger.info(request.toString());
        return service.settlePendingBets(request.getStatus(), request.getResult());
    }







}

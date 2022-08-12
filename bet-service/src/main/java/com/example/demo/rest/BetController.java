package com.example.demo.rest;

import java.util.List;
import java.util.logging.Logger;

import com.example.demo.dto.BetData;
import com.example.demo.dto.BetSlip;
import com.example.demo.dto.BetSlipData;
import com.example.demo.rest.request.*;
import com.example.demo.rest.response.BetSlipResponse;
import com.example.demo.rest.response.NewBetResponse;
import com.example.demo.rest.response.OperationResponse;
import com.example.demo.service.BetDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bet")
@RequiredArgsConstructor
public class BetController {

    @Autowired
    BetDefaultService service;

    public static Logger logger= Logger.getLogger("global");

    @PostMapping("/all")
    public List<BetData> allBet(@RequestBody BetSlipByStatusRequest request) {
        return service.findBetsByStatus(request.getStatus());
    }


    @PostMapping
    public NewBetResponse create(@RequestBody NewBetRequest request) {
        logger.info(request.toString());
        BetData bet = service.createBet(new BetData(request.getName(), request.getHomeOdd(), request.getAwayOdd(), request.getDrawOdd()));
        return new NewBetResponse(bet, "Created Successfully");
    }

    @PostMapping("/slip")
    public BetSlipResponse createBetSlip(@RequestBody NewBetSlipRequest request) {
        BetSlip betSlip = service.createBetSlip(new BetSlipData(request.getAccountId(), request.getStake(), request.getBetItems()));
        return new BetSlipResponse(betSlip, "Created Successfully");
    }

    @GetMapping("/slip/{id}")
    public BetSlipResponse getBetSlip(@PathVariable("account") Long id) {
        BetSlip betSlip = service.getBetSlip(id);
        return new BetSlipResponse(betSlip, "Retrived Successfully");
    }

    @GetMapping("/slip/account/{accountId}")
    public List<BetSlip> allBetSlips(@PathVariable("accountId") Long accountId) {
        return service.allBetSlips(accountId);
    }

    @PostMapping("/slip/by/status")
    public List<BetSlip> allBetSlips(@RequestBody BetSlipByStatusRequest request) {
        return service.getBetSlipsByStatus(request.getStatus());
    }

    @PostMapping("/settle-single-bet")
    public OperationResponse settleSingleBet(@RequestBody SettleSingleBetRequest request) {
        logger.info(request.toString());
        return service.settleSingleBet(request.getBetId(), request.getResult());
    }
    @PostMapping("/settle-pending-bets")
    public OperationResponse settlePendingBets(@RequestBody SettlePendingBetsRequest request) {
        logger.info(request.toString());
        return service.settlePendingBets(request.getStatus(), request.getResult());
    }







}

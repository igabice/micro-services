package com.example.demo.rest;

import java.util.List;
import java.util.logging.Logger;

import com.example.demo.dto.BetData;
import com.example.demo.dto.BetSlip;
import com.example.demo.dto.BetSlipData;
import com.example.demo.exception.InvalidRequestException;
import com.example.demo.rest.request.*;
import com.example.demo.rest.response.BetSlipResponse;
import com.example.demo.rest.response.NewBetResponse;
import com.example.demo.rest.response.OperationResponse;
import com.example.demo.service.BetDefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        BetData bet = service.createBet(new BetData(request.getName(), request.getHomeOdd(), request.getAwayOdd(), request.getDrawOdd()));
        return new NewBetResponse(bet, "Created Successfully");
    }

    @PostMapping("/slip")
    public BetSlipResponse createBetSlip(@RequestBody NewBetSlipRequest request) {
        if (request == null || request.getStake() < 1) {
            throw new InvalidRequestException("INVALID_REQUEST! Stake amount lesser than allowed (1)");
        }
        if (request.getBetItems().isEmpty()) {
            throw new InvalidRequestException("INVALID_REQUEST! Please select a bet");
        }
        BetSlip betSlip = service.createBetSlip(new BetSlipData(request.getAccountId(), request.getStake(), request.getBetItems()));
        return new BetSlipResponse(betSlip, "Created Successfully");
    }

    @GetMapping("/slip/{id}")
    public BetSlipResponse getBetSlip(@PathVariable("id") Long id) {
        if (id == null) {
            throw new InvalidRequestException("INVALID_REQUEST! Missing slip id");
        }
        BetSlip betSlip = service.getBetSlip(id);
        return new BetSlipResponse(betSlip, "Retrived Successfully");
    }

    @GetMapping("/slip/account/{accountId}")
    public List<BetSlip> allBetSlips(@PathVariable("accountId") Long accountId) {
        if (accountId == null) {
            throw new InvalidRequestException("INVALID_REQUEST! accountId required");
        }
        return service.allBetSlips(accountId);
    }

    @PostMapping("/slip/by/status")
    public List<BetSlip> allBetSlips(@RequestBody BetSlipByStatusRequest request) {
        if (request == null || request.getStatus() == null) {
            throw new InvalidRequestException("INVALID_REQUEST! result required");
        }
        return service.getBetSlipsByStatus(request.getStatus());
    }

    @PostMapping("/settle-single-bet")
    public OperationResponse settleSingleBet(@RequestBody SettleSingleBetRequest request) {
        if (request == null || request.getResult() == null) {
            throw new InvalidRequestException("INVALID_REQUEST! result required");
        }
        return service.settleSingleBet(request.getBetId(), request.getResult());
    }






}

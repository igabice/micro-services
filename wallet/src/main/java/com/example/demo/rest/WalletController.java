package com.example.demo.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.Balance;
import com.example.demo.dto.MoneyTransferData;
import com.example.demo.dto.MoneyTransferResult;
import com.example.demo.rest.request.DepositRequest;
import com.example.demo.rest.request.LoginAccountRequest;
import com.example.demo.rest.request.NewAccountRequest;
import com.example.demo.rest.request.WithdrawRequest;
import com.example.demo.rest.response.BalanceResponse;
import com.example.demo.rest.response.OperationResponse;
import com.example.demo.rest.response.OperationsResponse;
import com.example.demo.service.WalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {
    @Autowired
    private final WalletService service;

    @PostMapping("deposit/{account}")
    public OperationResponse deposit(@PathVariable("account") Long account, DepositRequest request) {
        final MoneyTransferResult moneyTransferResult = service.debitAccount(new MoneyTransferData(account, BigDecimal.valueOf(request.getAmount()), request.getReference()));
        return new OperationResponse(moneyTransferResult.status().name(), moneyTransferResult.message());
    }

    @PostMapping("withdraw/{account}")
    public OperationResponse withdraw(@PathVariable("account") Long account, WithdrawRequest request) {
        final MoneyTransferResult moneyTransferResult = service.creditAccount(new MoneyTransferData(account, BigDecimal.valueOf(request.getAmount()), request.getReference()));
        return new OperationResponse(moneyTransferResult.status().name(), moneyTransferResult.message());
    }

    @PostMapping("account")
    public BalanceResponse create(@RequestBody NewAccountRequest request) {
        throw new IllegalStateException("Not implemented");
    }

    @PostMapping("find-account")
    public BalanceResponse findAccount(@RequestBody LoginAccountRequest request) {
        throw new IllegalStateException("Not implemented");
    }

    @GetMapping("balance/{account}")
    public BalanceResponse balance(@PathVariable("account") Long account) {
        final Balance balance = service.balance(account);
        return new BalanceResponse(balance.accountId(), balance.amount());
    }

    @GetMapping("operations/{account}")
    public OperationsResponse listOperations(@PathVariable("account") Long account, @RequestParam(value = "take", defaultValue = "20") int take,
                                             @RequestParam(value = "skip", defaultValue = "0") int skip) {
        throw new IllegalStateException("Not implemented");
    }

}

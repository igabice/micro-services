package com.example.demo.service;

import com.example.demo.dto.Balance;
import com.example.demo.dto.MoneyTransferData;
import com.example.demo.dto.MoneyTransferResult;
import com.example.demo.dto.UserData;
import com.example.demo.model.User;
import com.example.demo.rest.response.BalanceResponse;

public interface WalletService {
    MoneyTransferResult creditAccount(MoneyTransferData transferData);

    MoneyTransferResult debitAccount(MoneyTransferData transferData);

    BalanceResponse createUser(UserData user);

    BalanceResponse login(UserData user);

    Balance balance(Long accountId);


}

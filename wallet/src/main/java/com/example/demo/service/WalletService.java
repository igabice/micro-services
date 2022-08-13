package com.example.demo.service;

import com.example.demo.dto.Balance;
import com.example.demo.dto.MoneyTransferData;
import com.example.demo.dto.MoneyTransferResult;

public interface WalletService {
    MoneyTransferResult creditAccount(MoneyTransferData transferData);

    MoneyTransferResult debitAccount(MoneyTransferData transferData);

    Balance balance(Long accountId);

}

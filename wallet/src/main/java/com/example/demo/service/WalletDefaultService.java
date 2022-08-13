package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletDefaultService implements WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public MoneyTransferResult creditAccount(MoneyTransferData transferData) {

        Wallet wallet = walletRepository.findFirstByAccountId(transferData.accountId());
        if(wallet == null){
            return new MoneyTransferResult(transferData.accountId(), TransferStatusType.FAIL, "INVALID_ACCOUNT_ID");
        }
        wallet.setBalance(wallet.getBalance() + transferData.amount().doubleValue());
        walletRepository.save(wallet);
        transactionRepository.save(
            new Transaction(transferData.accountId(), transferData.amount().doubleValue(), transferData.reference(), TransferStatusType.OK, TransactionType.DEPOSIT)
            );
        return new MoneyTransferResult(transferData.accountId(), TransferStatusType.OK, "DEPOSIT_SUCCESSFUL");
    }

    @Override
    public MoneyTransferResult debitAccount(MoneyTransferData transferData) {
        Wallet wallet = walletRepository.findFirstByAccountId(transferData.accountId());
        if(wallet == null){
            return new MoneyTransferResult(transferData.accountId(), TransferStatusType.FAIL, "INVALID_ACCOUNT_ID");
        }
        if(wallet.getBalance() < transferData.amount().doubleValue()){
            return new MoneyTransferResult(transferData.accountId(), TransferStatusType.FAIL, "INSUFFICIENT_BALANCE");
        }
        wallet.setBalance(wallet.getBalance() - transferData.amount().doubleValue());
        walletRepository.save(wallet);
        transactionRepository.save(
            new Transaction(transferData.accountId(), transferData.amount().doubleValue(), transferData.reference(), TransferStatusType.OK, TransactionType.WITHDRAWAL)
            );
        return new MoneyTransferResult(transferData.accountId(), TransferStatusType.OK, "WITHDRAWAL_SUCCESSFUL");
    }

    @Override
    public Balance balance(Long accountId) {
        Wallet wallet = walletRepository.findFirstByAccountId(accountId);
        if(wallet == null){
            return new Balance(accountId, new BigDecimal(0));
        }
        return new Balance(accountId, new BigDecimal(wallet.getBalance()));
    }
}

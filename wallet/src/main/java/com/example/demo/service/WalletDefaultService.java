package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.model.Wallet;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.rest.response.BalanceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletDefaultService implements WalletService {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public MoneyTransferResult creditAccount(MoneyTransferData transferData) {

        Wallet wallet = walletRepository.findFirstByAccountId(transferData.accountId());
        if(wallet == null){
            return new MoneyTransferResult(transferData.accountId(), TransferStatusType.FAIL, "INVALID_ACCOUNT_ID");
        }
        double amount = transferData.amount().doubleValue();
        if(amount < 1 ){
            return new MoneyTransferResult(transferData.accountId(), TransferStatusType.FAIL, "INVALID_AMOUNT");
        }
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
        transactionRepository.save(
            new Transaction(transferData.accountId(), amount, transferData.reference(), TransferStatusType.OK, TransactionType.DEPOSIT)
            );
        return new MoneyTransferResult(transferData.accountId(), TransferStatusType.OK, "DEPOSIT_SUCCESSFUL");
    }

    @Override
    public MoneyTransferResult debitAccount(MoneyTransferData transferData) {
        Wallet wallet = walletRepository.findFirstByAccountId(transferData.accountId());
        double amount = transferData.amount().doubleValue();
        if(amount < 1 ){
            return new MoneyTransferResult(transferData.accountId(), TransferStatusType.FAIL, "INVALID_AMOUNT");
        }
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
    public BalanceResponse createUser(UserData userData) {
        Optional<User> user = userRepository.findByEmail(userData.email());
        if(user.isPresent()){
            return new BalanceResponse(0L, BigDecimal.valueOf(0), "EMAIL_EXISTS");
        }
        User newUser = userRepository.save(new User(userData.email(), userData.password()));
        Wallet wallet = walletRepository.save(new Wallet( newUser.getId(), 0));
        return new BalanceResponse(newUser.getId(), BigDecimal.valueOf(wallet.getBalance()), "ACCOUNT_CREATED");
    }


    @Override
    public BalanceResponse login(UserData userData) {
        Optional<User> user = userRepository.findByEmailAndPassword(userData.email(), userData.password());
        if(user.isPresent()){
            Wallet wallet = walletRepository.findFirstByAccountId(user.get().getId());
            return new BalanceResponse(user.get().getId(), BigDecimal.valueOf(wallet.getBalance()), "LOGIN_SUCCESSFUL");
        }
        return new BalanceResponse(user.get().getId(), BigDecimal.valueOf(0), "LOGIN_FAILED");
    }


    @Override
    public Balance balance(Long accountId) {
        Wallet wallet = walletRepository.findFirstByAccountId(accountId);
        if(wallet == null){
            return new Balance(accountId, new BigDecimal(0));
        }
        return new Balance(accountId, BigDecimal.valueOf(wallet.getBalance()));
    }
}

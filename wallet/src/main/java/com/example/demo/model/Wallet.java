package com.example.demo.model;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {

    public Wallet(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long accountId;
    private double balance;

    public Wallet(Long accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WalletData{" +
                "id=" + id +
                ", name='" + accountId + '\'' +
                ", homeOdd=" + balance +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
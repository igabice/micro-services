package com.example.demo.model;

import com.example.demo.dto.TransactionType;
import com.example.demo.dto.TransferStatusType;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    public Transaction(){}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NonNull
    private Long accountId;
    @NonNull
    private String reference;
    private double amount;
    @NonNull
    private TransferStatusType status = TransferStatusType.OK;
    @NonNull
    private TransactionType type;

    public Transaction(Long accountId, double amount, String reference, TransferStatusType status, TransactionType type) {
        this.accountId = accountId;
        this.amount = amount;
        this.reference = reference;
        this.status = status;
        this.type = type;
    }

    public TransferStatusType getStatus() {
        return status;
    }

    public void setStatus(TransferStatusType status) {
        this.status = status;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
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
    
}
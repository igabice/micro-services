package com.example.demo.model;

import lombok.NonNull;

import javax.persistence.*;

import java.util.Date;


@Entity
@Table(name = "betslip")
public class BetSlip {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long accountId;
    private String status  = "pending";
    private String result  = "pending";
    private double totalOdd;
    private double stake;
    private String createdAt;

    protected BetSlip() {}

    public BetSlip(Long accountId, String status, String result, double totalOdd) {
        this.accountId = accountId;
        this.status = status;
        this.result = result;
        this.totalOdd = totalOdd;
    }
    public BetSlip(Long accountId, double stake, double totalOdd) {
        this.accountId = accountId;
        this.stake = stake;
        this.totalOdd = totalOdd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTotalOdd(double totalOdd) {
        this.totalOdd = totalOdd;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BetSlip{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", accountId='" + accountId + '\'' +
                ", result='" + result + '\'' +
                ", totalOdd=" + totalOdd +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public double getTotalOdd() {
        return totalOdd;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
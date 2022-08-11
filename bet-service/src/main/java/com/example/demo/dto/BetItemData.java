package com.example.demo.dto;

import javax.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "bet_item")
public class BetItemData {

    public BetItemData(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long betId;
    private Long betSlipId;
    private String position;
    private String status  = "pending";

    public BetItemData(Long betId, Long betSlipId, String position) {
        this.betId = betId;
        this.betSlipId = betSlipId;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public Long getBetSlipId() {
        return betSlipId;
    }

    public void setBetSlipId(Long betSlipId) {
        this.betSlipId = betSlipId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
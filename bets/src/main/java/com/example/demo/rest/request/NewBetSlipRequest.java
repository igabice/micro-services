package com.example.demo.rest.request;

import lombok.Data;

import java.util.List;

@Data
public class NewBetSlipRequest {
    private Long accountId;
    private double stake;
    private List<BetItem> betItems;
}

package com.example.demo.dto;

import com.example.demo.rest.request.BetItem;

import java.util.List;

public record BetSlipData(Long accountId, double stake, List<BetItem> betItems) {
}

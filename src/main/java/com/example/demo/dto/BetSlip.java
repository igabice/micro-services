package com.example.demo.dto;

import java.math.BigDecimal;

public record BetSlip(Long accountId, BigDecimal stake, BetSlipStatusType status) {
}

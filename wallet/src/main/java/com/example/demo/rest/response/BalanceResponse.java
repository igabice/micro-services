package com.example.demo.rest.response;

import java.math.BigDecimal;

public record BalanceResponse(Long accountId, BigDecimal amount, String message) {
}

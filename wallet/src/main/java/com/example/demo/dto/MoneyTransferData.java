package com.example.demo.dto;

import java.math.BigDecimal;

public record MoneyTransferData(Long accountId, BigDecimal amount, String reference) {
}

package com.example.demo.dto;

import java.math.BigDecimal;

public record Balance(Long accountId, BigDecimal amount) {
}

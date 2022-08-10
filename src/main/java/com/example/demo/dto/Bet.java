package com.example.demo.dto;

import java.math.BigDecimal;

public record Bet(String name, BetStatusType status, String outcome, int homeOdd, int awayOdd, int drawOdd) {
}

package com.example.demo.rest.response;


public record BalanceResponse(Long accountId, double amount, String message) {
}

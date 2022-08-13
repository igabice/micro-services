package com.example.demo.dto;


public record MoneyTransferResult(Long accountId, TransferStatusType status, String message) {
}

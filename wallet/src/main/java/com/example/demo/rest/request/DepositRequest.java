package com.example.demo.rest.request;

import lombok.Data;

@Data
public class DepositRequest {
    private double amount;
    private String reference;
}

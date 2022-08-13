package com.example.demo.rest.request;

import lombok.Data;

@Data
public class WithdrawRequest {
    private Long amount;
    private String reference;
}

package com.example.demo.rest.request;

import lombok.Data;

@Data
public class SettlePendingBetsRequest {
    private String status = "pending";
    private String result;
}

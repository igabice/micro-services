package com.example.demo.rest.request;

import lombok.Data;

@Data
public class BetItem {
    private String position;
    private Long betId;
    private double odd;

}

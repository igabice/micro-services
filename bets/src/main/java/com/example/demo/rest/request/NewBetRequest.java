package com.example.demo.rest.request;

import lombok.Data;

@Data
public class NewBetRequest {
    private String name;
    private double homeOdd;
    private double awayOdd;
    private double drawOdd;
}

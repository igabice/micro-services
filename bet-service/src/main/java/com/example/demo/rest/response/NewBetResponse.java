package com.example.demo.rest.response;

import com.example.demo.dto.BetData;

public record NewBetResponse(BetData betData, String message) {
}

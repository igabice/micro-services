package com.example.demo.rest.response;

import com.example.demo.model.Bet;

public record NewBetResponse(Bet bet, String message) {
}

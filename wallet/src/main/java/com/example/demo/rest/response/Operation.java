package com.example.demo.rest.response;

import java.math.BigDecimal;

public record Operation(String type, BigDecimal amount, String reference) {

}

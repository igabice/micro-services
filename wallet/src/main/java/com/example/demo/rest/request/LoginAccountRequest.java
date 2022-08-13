package com.example.demo.rest.request;

import lombok.Data;

@Data
public class LoginAccountRequest {
    private String email;
    private String password;
}

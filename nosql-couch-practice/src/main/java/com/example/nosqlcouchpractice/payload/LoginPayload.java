package com.example.nosqlcouchpractice.payload;

import lombok.Data;

@Data
public class LoginPayload {
    private String userName;
    private String password;
}

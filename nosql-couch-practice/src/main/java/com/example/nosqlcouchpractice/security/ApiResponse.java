package com.example.nosqlcouchpractice.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ApiResponse {
    private int status;
    private String message;
    private Object result;
}

package com.example.Erida.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String emailAddress;
    private String password;
}

package com.example.Erida.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRegistrationRequest {

    private String name;
    private String emailAddress;
    private String password;
}

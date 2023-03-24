package com.example.Erida.dtos.request;

import com.example.Erida.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerRegistrationRequest {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String emailAddress;
    private String phoneNumber;
    private String password;
}

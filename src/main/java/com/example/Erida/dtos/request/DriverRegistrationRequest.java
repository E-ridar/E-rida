package com.example.Erida.dtos.request;

import com.example.Erida.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverRegistrationRequest {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String emailAddress;
    private String password;
}

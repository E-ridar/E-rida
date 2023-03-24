package com.example.Erida.data.models;

import com.example.Erida.enums.Gender;
import com.example.Erida.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    private String emailAddress;
    private String phoneNumber;
    private String password;
    private Boolean isDisabled = true;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    public Passenger(String firstName, String lastName, Gender gender, String phoneNumber, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;

    }

}
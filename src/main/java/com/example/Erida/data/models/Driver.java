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
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    private String emailAddress;
    private String password;
    private Boolean isDisabled = true;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankInfo bankInfo;

    @Enumerated(value = EnumType.STRING)
    private Status status;


    public Driver(String firstName, String lastName, Gender gender,
                  String phoneNumber, String emailAddress, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;

    }
}

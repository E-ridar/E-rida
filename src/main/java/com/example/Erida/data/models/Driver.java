package com.example.Erida.data.models;

import com.example.Erida.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private int age;
    private String name;
    private String password;
    private String email;
    private Role role;
    private Instant createdAt;
    private Boolean isEnabled;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankInfo bankInfo;


}

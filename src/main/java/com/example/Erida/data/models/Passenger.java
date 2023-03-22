package com.example.Erida.data.models;

import com.example.Erida.enums.Gender;
import com.example.Erida.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    private Role role;
    private Instant createdAt;
    private Boolean isEnabled;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;


    public Passenger(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

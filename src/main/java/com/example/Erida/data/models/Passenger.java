package com.example.Erida.data.models;

import com.example.Erida.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private AppUser appUser;
}

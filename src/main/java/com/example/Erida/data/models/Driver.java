package com.example.Erida.data.models;

import jakarta.persistence.*;
import lombok.*;

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
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private BankInfo bankInfo;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AppUser appUser;

}

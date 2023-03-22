package com.example.Erida.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class VerificationOTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oneTimePassword;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime verifiedAt;
    @ManyToOne
    @JoinColumn(name = "passenger-id", referencedColumnName = "id")
    private Passenger passenger;

    public VerificationOTP(String oneTimePassword, LocalDateTime createdAt, LocalDateTime expiredAt, Passenger passenger) {
        this.oneTimePassword = oneTimePassword;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.passenger= passenger;
    }
}

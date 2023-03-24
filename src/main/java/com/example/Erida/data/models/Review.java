package com.example.Erida.data.models;

import com.example.Erida.enums.Rating;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedBack;

    @Enumerated(value = EnumType.STRING)
    private Rating rating;
    private Long rater;
    private Long ratee;
}

package com.example.Erida.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditCardRequest {


    private String cardName;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}

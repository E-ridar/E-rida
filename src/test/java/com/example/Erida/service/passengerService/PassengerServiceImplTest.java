package com.example.Erida.service.passengerService;

import com.example.Erida.dtos.request.PassengerRegistrationRequest;
import com.example.Erida.service.Registration.RegistrationService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PassengerServiceImplTest {

    @Autowired
    private PassengerService passengerService;

    private PassengerRegistrationRequest passengerRegistrationRequest;
    @Autowired
    private RegistrationService registrationService;


    @BeforeEach
    void setUp(){
        passengerRegistrationRequest = new PassengerRegistrationRequest();
        passengerRegistrationRequest.setName("Racheal");
        passengerRegistrationRequest.setEmailAddress("Racheal@gmail.com");
        passengerRegistrationRequest.setPassword("Racheal1@");
    }

    @Test
    void createAccount() throws MessagingException {
        assertEquals(registrationService.passengerRegister(passengerRegistrationRequest), "Sign up Successful....Otp sent to your mail");
    }

    @Test
    void login() {
    }
}
package com.example.Erida.service.Registration;

import com.example.Erida.dtos.request.DriverRegistrationRequest;
import com.example.Erida.dtos.request.PassengerRegistrationRequest;
import com.example.Erida.dtos.request.SendOTPRequest;
import com.example.Erida.dtos.request.VerifyOTPRequest;
import jakarta.mail.MessagingException;

public interface RegistrationService {
    String passengerRegister(PassengerRegistrationRequest registrationRequest) throws MessagingException;

    String driverRegister(DriverRegistrationRequest registrationRequest);

    String verifyOTP(VerifyOTPRequest verifyOTPRequest);

    String resendVerificationOTP(SendOTPRequest sendOTPRequest) throws MessagingException;


}

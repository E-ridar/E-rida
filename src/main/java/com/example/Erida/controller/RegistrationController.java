package com.example.Erida.controller;

import com.example.Erida.dtos.request.PassengerRegistrationRequest;
import com.example.Erida.dtos.request.SendOTPRequest;
import com.example.Erida.dtos.request.VerifyOTPRequest;
import com.example.Erida.service.Registration.RegistrationService;
import com.example.Erida.utils.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {



        @Autowired
        private RegistrationService registrationService;
        @PostMapping("/register")
        public ResponseEntity<?> passengerRegister(@RequestBody PassengerRegistrationRequest registrationRequest,
                                          HttpServletRequest httpServletRequest) throws MessagingException {
            String createUser = registrationService.passengerRegister(registrationRequest);
            ApiResponse apiResponse = ApiResponse.builder()
                    .timeStamp(ZonedDateTime.now())
                    .data(createUser)
                    .path(httpServletRequest.getRequestURI())
                    .statusCode(HttpStatus.OK.value())
                    .isSuccessful(true)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }


    @PostMapping("/verify")
    public ResponseEntity<?>verifyOTP(@RequestBody VerifyOTPRequest verifyOTPRequest,
                                      HttpServletRequest httpServletRequest){

        registrationService.verifyOTP(verifyOTPRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .path(httpServletRequest.getRequestURI())
                .data(registrationService.verifyOTP(verifyOTPRequest))
                .isSuccessful(true)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("/resend-OTP")
    public ResponseEntity<?>resendVerificationOTP(@RequestBody SendOTPRequest sendOTPRequest, HttpServletRequest httpServletRequest) throws MessagingException{
        var oTP = registrationService.resendVerificationOTP(sendOTPRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .isSuccessful(true)
                .data(oTP)
                .statusCode(HttpStatus.OK.value())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}

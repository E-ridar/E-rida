package com.example.Erida.service.verifyOtp;

import com.example.Erida.data.models.VerificationOTP;

import java.util.Optional;

public interface VerificationOTPService {

    void saveVerificationOTP(VerificationOTP verificationOTP);
    Optional<VerificationOTP> findByOTP(String otp);

    void setVerifiedAt(String otp);

    void deleteOtp();
}

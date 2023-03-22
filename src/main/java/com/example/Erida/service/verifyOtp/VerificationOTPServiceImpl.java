package com.example.Erida.service.verifyOtp;

import com.example.Erida.data.models.VerificationOTP;
import com.example.Erida.data.repository.VerificationOTPRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationOTPServiceImpl implements VerificationOTPService {

    @Autowired
    VerificationOTPRepo verificationOTPRepo;

    @Override
    public void saveVerificationOTP(VerificationOTP verificationOTP) {
        verificationOTPRepo.save(verificationOTP);
    }


    @Override
    public Optional<VerificationOTP> findByOTP(String otp) {
        return verificationOTPRepo.findVerificationOTPByOneTimePassword(otp);
    }

    @Override
    public void setVerifiedAt(String otp) {
        verificationOTPRepo.setVerifiedAt(LocalDateTime.now(), otp);
    }

    @Override
    public void deleteOtp() {
        verificationOTPRepo.deleteVerificationOTPByExpiredAtBefore(LocalDateTime.now());

    }

}

package com.example.Erida.service.driver;

import com.example.Erida.data.models.Driver;
import com.example.Erida.data.models.VerificationOTP;
import com.example.Erida.data.repository.DriverRepo;
import com.example.Erida.dtos.request.LoginRequest;
import com.example.Erida.exceptions.GenericHandlerException;
import com.example.Erida.service.verifyOtp.VerificationOTPService;
import com.example.Erida.utils.OTPGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private VerificationOTPService verificationOTPService;

    @Override
    public String createAccount(Driver driver) {
        Driver savedDriver = saveUser(driver);
        String oTP = OTPGenerator.generateOTP().toString();
        VerificationOTP verificationOTP = new VerificationOTP(
                oTP,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                savedDriver
        );
        verificationOTPService.saveVerificationOTP(verificationOTP);
        return oTP;
    }

    @Override
    public Driver saveUser(Driver driver) {
    return driverRepo.save(driver);
    }

    @Override
    public Optional<Driver> findUserByEmailIgnoreCase(String emailAddress) {
       return driverRepo.findUserByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Driver foundUser = driverRepo.findUserByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(()-> new GenericHandlerException("User with " + loginRequest.getEmailAddress() + "does not exist "));
        if (foundUser.getIsDisabled())throw new GenericHandlerException("Verify account");
        if (!BCrypt.checkpw(loginRequest.getPassword(), foundUser.getPassword()))throw new GenericHandlerException("Incorrect Password");
        return "Login successful";
    }


}

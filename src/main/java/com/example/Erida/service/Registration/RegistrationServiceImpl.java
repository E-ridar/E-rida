package com.example.Erida.service.Registration;

import com.example.Erida.data.models.Driver;
import com.example.Erida.data.models.Passenger;
import com.example.Erida.data.models.VerificationOTP;
import com.example.Erida.data.repository.DriverRepo;
import com.example.Erida.data.repository.PassengerRepo;
import com.example.Erida.dtos.request.DriverRegistrationRequest;
import com.example.Erida.dtos.request.PassengerRegistrationRequest;
import com.example.Erida.dtos.request.SendOTPRequest;
import com.example.Erida.dtos.request.VerifyOTPRequest;
import com.example.Erida.email.EmailSender;
import com.example.Erida.exceptions.GenericHandlerException;
import com.example.Erida.service.driver.DriverService;
import com.example.Erida.service.passengerService.PassengerService;
import com.example.Erida.service.verifyOtp.VerificationOTPService;
import com.example.Erida.utils.OTPGenerator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DriverService driverService;

    private DriverRegistrationRequest driverRegistrationRequest;


    @Autowired
    private PassengerRepo passengerRepo;

    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private VerificationOTPService verificationOTPService;

    @Autowired
    private EmailSender emailSender;
    @Override
    public String passengerRegister(PassengerRegistrationRequest passengerRegistrationRequest) throws MessagingException {
        boolean isExist = passengerService.findUserByEmailAddress(passengerRegistrationRequest.getEmailAddress())
                .isPresent();
        if (isExist)throw new GenericHandlerException("User with email already exist");
        String oTP =  passengerService.createAccount(new Passenger(
                passengerRegistrationRequest.getFirstName(),
                passengerRegistrationRequest.getLastName(),
                passengerRegistrationRequest.getGender(),
                passengerRegistrationRequest.getPhoneNumber(),
                passengerRegistrationRequest.getEmailAddress(),
                BCrypt.hashpw(passengerRegistrationRequest.getPassword(),BCrypt.gensalt())

        ));

        emailSender.send(passengerRegistrationRequest.getEmailAddress(), buildEmail(passengerRegistrationRequest.getFirstName(), oTP));
        return oTP;
    }

    @Override
    public String driverRegister(DriverRegistrationRequest registrationRequest) {
        if (registrationRequest == null) {
            throw new IllegalArgumentException("DriverRegistrationRequest cannot be null");
        }
        boolean isExist = driverService.findUserByEmailIgnoreCase(registrationRequest.getEmailAddress())
                .isPresent();
        if (isExist) {
            throw new GenericHandlerException("User with email already exists");
        }
        String oTP =  driverService.createAccount(new Driver(
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getGender(),
                registrationRequest.getPhoneNumber(),
                registrationRequest.getEmailAddress(),
                BCrypt.hashpw(registrationRequest.getPassword(), BCrypt.gensalt())

        ));
        emailSender.send(registrationRequest.getEmailAddress(), buildEmail(registrationRequest.getFirstName(), oTP));
        return oTP;
    }




    @Override
    public String verifyOTP(VerifyOTPRequest verifyOTPRequest) {
        VerificationOTP otp = verificationOTPService.findByOTP(verifyOTPRequest.getOneTimePassword())
                .orElseThrow(()-> new GenericHandlerException("oneTimePassword is invalid"));

        if(otp.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new GenericHandlerException("Token has expired");
        }

        if(otp.getVerifiedAt() != null){
            throw new GenericHandlerException("Token has been used");
        }
        verificationOTPService.setVerifiedAt(otp.getOneTimePassword());
        passengerService.enableUser(verifyOTPRequest.getEmailAddress());

        return "verified";
    }

    @Override
    public String resendVerificationOTP(SendOTPRequest sendOTPRequest) throws MessagingException {
        Passenger foundUser= passengerRepo.findUserByEmailAddressIgnoreCase(sendOTPRequest.getEmailAddress())
                .orElseThrow(()->
                        new GenericHandlerException("User with this" + sendOTPRequest.getEmailAddress() +"does not exist"));
        if (!foundUser.getIsDisabled()) throw new GenericHandlerException("You are already a verified user");
        String oTP = OTPGenerator.generateOTP().toString();
        VerificationOTP verificationOTP = new VerificationOTP(
                oTP,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                foundUser
        );
        verificationOTPService.saveVerificationOTP(verificationOTP);
        emailSender.send(sendOTPRequest.getEmailAddress(), buildEmail(foundUser.getFirstName(), oTP));

        return oTP;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + link + "</p></blockquote>\n Link will expire in 10 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}


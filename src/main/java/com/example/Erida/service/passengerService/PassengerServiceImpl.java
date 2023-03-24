package com.example.Erida.service.passengerService;

import com.example.Erida.data.models.Card;
import com.example.Erida.data.models.Passenger;
import com.example.Erida.data.models.VerificationOTP;
import com.example.Erida.data.repository.PassengerRepo;
import com.example.Erida.dtos.request.*;
import com.example.Erida.dtos.response.AccountVerificationPaystackResponse;
import com.example.Erida.dtos.response.BVNValidationPaystackResponse;
import com.example.Erida.dtos.response.BankName;
import com.example.Erida.exceptions.GenericHandlerException;
import com.example.Erida.service.card.CardService;
import com.example.Erida.service.verifyOtp.VerificationOTPService;
import com.example.Erida.utils.OTPGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class PassengerServiceImpl implements PassengerService{
    @Autowired
    private PassengerRepo passengerRepo;


    private CardService cardService;

    @Autowired
    private VerificationOTPService verificationOTPService;

    private final String SECRET_KEY = System.getenv("PAYSTACK_SECRET_KEY");


    @Override
    public String createAccount(Passenger passenger) {
        Passenger savedPassenger = saveUser(passenger);
        String oTP = OTPGenerator.generateOTP().toString();
        VerificationOTP verificationOTP = new VerificationOTP(
                oTP,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                savedPassenger
        );
        verificationOTPService.saveVerificationOTP(verificationOTP);
        return oTP;
    }


    @Override
    public String login(LoginRequest loginRequest) {
       Passenger foundUser = passengerRepo.findUserByEmailAddressIgnoreCase(loginRequest.getEmailAddress())
                .orElseThrow(()-> new GenericHandlerException("User with " + loginRequest.getEmailAddress() + "does not exist "));
        if (foundUser.getIsDisabled())throw new GenericHandlerException("Verify account");
        if (!BCrypt.checkpw(loginRequest.getPassword(), foundUser.getPassword()))throw new GenericHandlerException("Incorrect Password");
        return "Login successful";
    }

    @Override
    public Optional<Passenger> findUserByEmailAddress(String email) {
        return passengerRepo.findUserByEmailAddressIgnoreCase(email);
    }

    @Override
    public void enableUser(String emailAddress) {

        passengerRepo.enableUser(emailAddress);

}

    @Override
    public Passenger saveUser(Passenger passenger) {
        return passengerRepo.save(passenger);

    }

    @Override
    public List<Card> viewCards(Long passengerId) {
        return null;
    }

    @Override
    public String addCard(Long passengerId, AddCardRequest addCardRequest) throws ParseException, IOException {
        return null;
    }

    @Override
    public AccountVerificationPaystackResponse verifyReceiverAccount(AccountVerificationRequest accountVerificationRequest) throws IOException {
        return null;
    }

    @Override
    public List<BankName> getListOfBanks() throws IOException {
        return null;
    }

    @Override
    public BVNValidationPaystackResponse bvnValidation(BvnValidationRequest bvnValidationRequest) throws IOException {
        return null;
    }

    @Override
    public String getBankCode(BankCodeRequest bankCodeRequest) throws IOException {
        return null;
    }

    @Override
    public String createTransferRecipient(CreateTransferRecipientRequest createTransferRecipientRequest) throws IOException {
        return null;
    }

    @Override
    public JsonNode initiateTransfer(InitiateTransferRequest initiateTransferRequest) throws IOException {
        return null;
    }

    @Override
    public String deleteUser(Long passengerId, DeleteUserRequest deleteUserRequest)
    {
        Passenger foundUser = passengerRepo.findById(passengerId).get();
        if (BCrypt.checkpw(deleteUserRequest.getPassword(), foundUser.getPassword())){
            foundUser.setEmailAddress("deleted"+foundUser.getEmailAddress()+ UUID.randomUUID());
            cardService.deleteUserCards(passengerId);
            saveUser(foundUser);
            return "Account deleted successfully";
        }
        else throw new GenericHandlerException("Incorrect Password");
    }
}

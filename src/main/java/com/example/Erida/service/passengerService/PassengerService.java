package com.example.Erida.service.passengerService;

import com.example.Erida.data.models.Card;
import com.example.Erida.data.models.Passenger;
import com.example.Erida.dtos.request.*;
import com.example.Erida.dtos.response.AccountVerificationPaystackResponse;
import com.example.Erida.dtos.response.BVNValidationPaystackResponse;
import com.example.Erida.dtos.response.BankName;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface PassengerService  {
    String createAccount(Passenger passenger);

    String login(LoginRequest loginRequest);
    Optional<Passenger> findUserByEmailAddress(String email);

    void enableUser(String emailAddress);
    Passenger saveUser(Passenger passenger);

    List<Card> viewCards(Long passengerId);
    String addCard(Long passengerId, AddCardRequest addCardRequest) throws ParseException, IOException;
    AccountVerificationPaystackResponse verifyReceiverAccount(AccountVerificationRequest accountVerificationRequest) throws IOException;
    List<BankName> getListOfBanks() throws IOException;
    BVNValidationPaystackResponse bvnValidation(BvnValidationRequest bvnValidationRequest) throws IOException;
    String getBankCode(BankCodeRequest bankCodeRequest) throws IOException;
    String createTransferRecipient(CreateTransferRecipientRequest createTransferRecipientRequest) throws IOException;
    JsonNode initiateTransfer(InitiateTransferRequest initiateTransferRequest) throws IOException;

    String deleteUser(Long passengerId, DeleteUserRequest deleteUserRequest);

}

package com.example.Erida.service.driver;

import com.example.Erida.data.models.Driver;
import com.example.Erida.dtos.request.LoginRequest;

import java.util.Optional;

public interface DriverService {

    String createAccount(Driver driver);

    Driver saveUser(Driver driver);

    Optional<Driver> findUserByEmailIgnoreCase(String emailAddress);

    String login(LoginRequest loginRequest);
}

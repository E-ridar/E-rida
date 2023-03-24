package com.example.Erida.data.repository;

import com.example.Erida.data.models.Passenger;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findUserByEmailAddressIgnoreCase(String emailAddress);
    @Transactional
    @Modifying
    @Query("UPDATE Passenger passenger SET passenger.isDisabled = false WHERE passenger.emailAddress=?1")
    void enableUser(String emailAddress);

}

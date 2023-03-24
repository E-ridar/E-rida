package com.example.Erida.data.repository;

import com.example.Erida.data.models.Driver;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DriverRepo extends JpaRepository<Driver, Long> {

    Optional<Driver> findUserByEmailAddressIgnoreCase(String emailAddress);
    @Transactional
    @Modifying
    @Query("UPDATE Driver driver SET driver.isDisabled = false WHERE driver.emailAddress=?1")
    void enableUser(String emailAddress);
}

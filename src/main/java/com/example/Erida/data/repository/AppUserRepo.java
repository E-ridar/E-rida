package com.example.Erida.data.repository;

import com.example.Erida.data.models.AppUser;
import com.example.Erida.data.models.Passenger;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findUserByEmail(String emailAddress);

}

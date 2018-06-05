package com.eventplaner.model.repositories;

import com.eventplaner.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository("registeredUserRepository")
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    RegisteredUser findByEmail(String email);
    RegisteredUser findByUserID(String userID);
}

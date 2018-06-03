package com.eventplaner.model.repositories;

import com.eventplaner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserID(String userID);
    User findByEmail(String email);
}

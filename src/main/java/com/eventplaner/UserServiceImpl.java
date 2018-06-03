package com.eventplaner;

import java.util.Arrays;
import java.util.HashSet;

import com.eventplaner.model.RegisteredUser;
import com.eventplaner.model.User;
import com.eventplaner.model.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisteredUser user = registeredUserRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        System.out.println("seas");
        return new UserPrincipal(user);
    }

}
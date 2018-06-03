package com.eventplaner;

import com.eventplaner.model.RegisteredUser;

public interface UserService {
    public RegisteredUser findUserByEmail(String email);
    public void saveUser(RegisteredUser user);
}

package com.datlq.auth_service.security;

import org.springframework.stereotype.Component;

@Component
public class SHAHashing implements Hashing {
    private final String salt = "RandomSalt";

    @Override
    public String hashedPassword(String password) {
        return password + salt;
    }

    @Override
    public boolean validatePassword(String password, String hashedPassword) {
        String correctPassword = hashedPassword.substring(0, hashedPassword.length() - salt.length());
        return correctPassword.equals(password);
    }
}

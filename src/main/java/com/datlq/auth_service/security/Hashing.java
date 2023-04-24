package com.datlq.auth_service.security;

public interface Hashing {
    public String hashedPassword(String password);
    public boolean validatePassword(String password, String hashedPassword);
}

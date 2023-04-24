package com.datlq.auth_service.services;

import com.datlq.auth_service.models.User;

import java.util.Optional;

public interface UserService {
    public User login(String email, String password);
    public boolean logout(String email);
    public User addUser(String email, String fullname, String password);
    public User addUserThenActive(String email, String fullname, String password);
    public Boolean activateUser(String activateCode);
    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String email, String newEmail);
    public Optional<User> findByEmail(String email);
    public User findById(String id);
}

package com.datlq.auth_service.services;

import com.datlq.auth_service.exception.UserException;
import com.datlq.auth_service.models.State;
import com.datlq.auth_service.models.User;
import com.datlq.auth_service.repositories.UserRepository;
import com.datlq.auth_service.security.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceInMemory implements UserService {
    private UserRepository userRepo;
    private Hashing hashing;

    @Override
    public User login(String email, String password) {
        Optional<User> o_user = userRepo.findUserByEmail(email);
        if (o_user.isEmpty()) {
            throw new UserException("User not found");
        }
        User user = o_user.get();
        if (user.getState() != State.ACTIVE) {
            throw new UserException("User is inactive");
        }
        if (hashing.validatePassword(password, o_user.get().getHashedPassword())) {
            return o_user.get();
        }
        throw new UserException("Invalid email or password");
    }

    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String email, String fullname, String password) {
        return userRepo.addUser(email, fullname, hashing.hashedPassword(password));
    }

    @Override
    public User addUserThenActive(String email, String fullname, String password) {
        return userRepo.addUser(email, fullname, hashing.hashedPassword(password), State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activateCode) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}

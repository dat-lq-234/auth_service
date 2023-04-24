package com.datlq.auth_service;

import com.datlq.auth_service.models.State;
import com.datlq.auth_service.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import com.datlq.auth_service.models.User;

import static org.assertj.core.api.Assertions.*;

public class TestUserRepository {
    @Test
    public void addUser() {
        UserRepository userRepo = new UserRepository();
        User user = userRepo.addUser("a@gmail.com", "Le Van A", "asd123easdqwe", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    public void addUserWithoutState() {
        UserRepository userRepo = new UserRepository();
        User user = userRepo.addUser("a@gmail.com", "Le Van A", "asd123easdqwe");
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExisted() {
        UserRepository userRepo = new UserRepository();
        userRepo.addUser("a@gmail.com", "Le Van A", "asd123easdqwe");
        assertThat(userRepo.isEmailExisted("a@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExisted("b@gmail.com")).isFalse();
    }

    @Test
    public void findUserByEmail(){
        UserRepository userRepo = new UserRepository();
        userRepo.addUser("a@gmail.com", "Le Van A", "asd123easdqwe");
        assertThat(userRepo.findUserByEmail("a@gmail.com")).isPresent();
        assertThat(userRepo.findUserByEmail("b@gmail.com")).isNotPresent();
    }
}

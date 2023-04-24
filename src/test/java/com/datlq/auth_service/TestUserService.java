package com.datlq.auth_service;

import com.datlq.auth_service.exception.UserException;
import com.datlq.auth_service.models.User;
import com.datlq.auth_service.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = userService.addUser("a@gmail.com", "Le Van A", "abc");
        assertThat(user).isNotNull();

    }

    @Test
    public void loginPendingAccount() {
        userService.addUser("a@gmail.com", "Le Van A", "abc");
        assertThatThrownBy(() -> {
            userService.login("a@gmail.com", "aaa");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User is inactive");
    }

    @Test
    public void loginIncorrectAccount() {
        userService.addUser("a@gmail.com", "Le Van A", "abc");
        assertThatThrownBy(() -> {
            userService.login("abc@gmail.com", "aaa");
        }).isInstanceOf(UserException.class)
                .hasMessageContaining("User not found");
    }
//
//    @Test
//    public void loginIncorrectPassword() {
//        userService.addUserThenActive("a@gmail.com", "Le Van A", "abc");
//        assertThatThrownBy(() -> {
//            userService.login("a@gmail.com", "aaa");
//        }).isInstanceOf(UserException.class)
//                .hasMessageContaining("Invalid email or password");
//    }

    @Test
    public void loginSuccess() {
        User user = userService.addUserThenActive("a@gmail.com", "Le Van A", "abc");
        assertThat(user).isNotNull();
    }
}

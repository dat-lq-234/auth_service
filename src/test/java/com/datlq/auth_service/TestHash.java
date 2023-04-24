package com.datlq.auth_service;

import com.datlq.auth_service.security.Hashing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TestHash {
    @Autowired
    private Hashing hashing;

    @Test
    public void hashedPassword(){
        List<String> passwords = new ArrayList<>(List.of("abc", "xyz", "123asd"));
        for (String pass: passwords){
            String hashedPassword = hashing.hashedPassword(pass);
            assertThat(hashedPassword).isNotNull();
        }
    }

    @Test
    public void validatePassword(){
        List<String> passwords = new ArrayList<>(List.of("abc", "xyz", "123asd"));
        for (String pass: passwords){
            String hashedPassword = hashing.hashedPassword(pass);
            assertThat(hashing.validatePassword(pass, hashedPassword)).isTrue();
        }

        assertThat(hashing.validatePassword("asdasd", "asd21easjdu2")).isFalse();

    }
}

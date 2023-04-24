package com.datlq.auth_service.repositories;

import com.datlq.auth_service.models.State;
import com.datlq.auth_service.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<String, User>();
    public User addUser(String email, String fullname, String hashedPassword) {
        return addUser(email, fullname, hashedPassword, State.PENDING);
    }
    public User addUser(String email, String fullname, String hashedPassword, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .email(email)
                .fullname(fullname)
                .state(state)
                .hashedPassword(hashedPassword)
                .build();
        users.put(id, user);
        return user;
    }

    public boolean isEmailExisted(String email){
        return users.values().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> findUserByEmail(String email){
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}

package com.dkasiian.model.services;

import com.dkasiian.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Long id);
    User saveUser(User user);
    List<User> getAllUsers();
    void deleteUserById(Long userId);
}

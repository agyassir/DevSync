package com.example.decs.Repository;

import com.example.decs.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User findByUsername(String username);
    User getById(long id);
    Optional<User> findByEmail(String email);
    User save(User user);
    List<User> getAll();
    void MinusCoins(User user,int count);
    void update(User user);
}

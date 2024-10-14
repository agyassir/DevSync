package com.example.decs;

import com.example.decs.Entity.User;
import com.example.decs.Repository.Implementation.UserRepositoryImpl;
import com.example.decs.Service.UserService;

public class main {
    public static void main(String[] args) {
        User user = new User();
        user.setFirstName("nabil");
        user.setLastName("Hakimi");
        user.setUsername("hahhaa");
        user.setEmail("nbail@g.com");
        user.setPassword("123");
        user.setIs_chef(true);
        new UserRepositoryImpl().save(user);
    }
}

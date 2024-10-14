package com.example.decs.Service;

import com.example.decs.Entity.User;
import com.example.decs.Repository.Implementation.UserRepositoryImpl;
import com.example.decs.Repository.UserRepository;

import jakarta.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {

    private UserRepository userRepository=new UserRepositoryImpl();

    public User signup(String username,String firstname,String lastname, String email, String password,boolean is_chef) {

        String hashedPassword = hashPassword(password);
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use.");
        } else if (userRepository.findByUsername(username)!=null) {
            throw new IllegalArgumentException("Username already in use.");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setFirstName(firstname);
        newUser.setLastName(lastname);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setIs_chef(is_chef);

        return userRepository.save(newUser);
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !checkPassword(password, user.getPassword())) {
            return false;
        }

        return true;
    }

    public User findUserbyUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll(){
        return userRepository.getAll();
    }

    public User findById(long id){
        return userRepository.getById(id);
    }

    private String hashPassword(String password) {
        // Implement password hashing logic (e.g., BCrypt)
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public void minusCoins(int count,User user){
        userRepository.MinusCoins(user,count);
    }
    public void incrementUserCoinsDaily() {
        List<User> users = userRepository.getAll(); // Fetch all users
        for (User user : users) {
            user.setCoins(user.getCoins() + 2);  // Increment coins by 2
            userRepository.update(user);         // Update the user
        }
    }
}


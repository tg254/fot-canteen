package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.User;
import com.hello.FirstApp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> readAllUsers(){
        return (List<User>) userRepo.findAll();
    }

    public Optional<User> readUser(Integer id){
        return userRepo.findById(id);
    }

    public boolean createUser(User user){
        userRepo.save(user);
        return true;
    }

    public boolean updateUser(User user){
        createUser(user);
        return true;
    }

    public boolean deleteUser(Integer id){
        userRepo.deleteById(id);
        return true;
    }

    public Optional<User> getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
}

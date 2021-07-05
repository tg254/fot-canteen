package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.User;
import com.hello.FirstApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService usersService;

    @GetMapping
    public List<User> getAllIUsers(){
        return usersService.readAllUsers();
    }

    @GetMapping(path ="{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id){
        return usersService.readUser(id);
    }

    @PostMapping
    public void postUser(@NotNull @RequestBody User user){
        usersService.createUser(user);
    }

    @PutMapping(path = "{id}")
    public void putUser(@PathVariable("id") Integer id, @NotNull @RequestBody User user){
        user.setId(id);
        usersService.updateUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        usersService.deleteUser(id);
    }
}

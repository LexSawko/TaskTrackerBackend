package com.codextask.backend.controller;

import com.codextask.backend.entity.User;
import com.codextask.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/{email}")
    public User getUser(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

//    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<User> getUsers(){
//        return userService.getUsers();
//    }
//
//    @GetMapping(value = "/user/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public User getUserById(@PathVariable String login){
//        return userService.getUserById(login);
//    }
//
    @PostMapping
    public void addUser(@RequestBody User user){
        System.out.println(user);
        userService.addUser(user);
    }
//
//    @PostMapping(value = "/user/{login}")
//    public void addUser(@PathVariable String login, @RequestBody User user){
//        if(login.equals(user.getLogin())){
//            userService.updateUser(user);
//        }
//    }
//
//    @DeleteMapping(value = "user/{login}")
//    public void deleteUser(@PathVariable String login){
//        userService.deleteUser(login);
//    }
}

package com.codextask.backend.service.impl;


import com.codextask.backend.entity.User;
import com.codextask.backend.repository.UserRepository;
import com.codextask.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public List<User> getUsers(){
       return userRepository.findAll();
    }

    @Transactional
    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    @Transactional
    public User getUserByNameAndSurname(String name, String surname){
        return userRepository.findUserByNameAndSurname(name, surname);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.delete(id);
    }

    @Transactional
    public void addUser(User user){
        user.setProjects(new HashSet<>());
        user.setComments(new ArrayList<>());
        user.setTasks(new ArrayList<>());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setToken(bCryptPasswordEncoder.encode(user.getEmail()));
        user.setVerified(false);
        userRepository.save(user);
    }

    @Transactional
    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}

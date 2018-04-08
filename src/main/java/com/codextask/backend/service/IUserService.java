package com.codextask.backend.service;

import com.codextask.backend.entity.User;

import java.util.Collection;
import java.util.List;

public interface IUserService {

     List<User> getUsers();

     User getUserById(Long id);

     User getUserByNameAndSurname(String name, String surname);

     void deleteUser(Long id);

     void addUser(User user);

     User getUserByEmail(String email);
}

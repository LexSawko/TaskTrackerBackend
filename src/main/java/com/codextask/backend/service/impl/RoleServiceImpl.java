package com.codextask.backend.service.impl;


import com.codextask.backend.entity.Role;
import com.codextask.backend.repository.RoleRepository;
import com.codextask.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{
    @Autowired
    RoleRepository repository;

    @Transactional
    public List<Role> getRoles(){
        return repository.findAll();
    }

}

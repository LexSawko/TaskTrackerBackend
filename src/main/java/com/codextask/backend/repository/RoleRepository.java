package com.codextask.backend.repository;

import com.codextask.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository  extends JpaRepository<Role,Long> {
    Role findByName(String role);
}

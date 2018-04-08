package com.codextask.backend.repository;

import com.codextask.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByNameAndSurname(@Param("name") String name, @Param("surname") String surname);
    User findUserByEmail(String email);
}

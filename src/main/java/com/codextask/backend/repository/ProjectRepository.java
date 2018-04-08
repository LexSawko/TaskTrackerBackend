package com.codextask.backend.repository;


import com.codextask.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends JpaRepository<Project,Long> {
//    spring.queries.roles-query=select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?
//    Set<Project> findAllByParticipantId(Long id);
    Project getProjectByName(@Param("name") String name);
}

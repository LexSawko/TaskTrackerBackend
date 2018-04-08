package com.codextask.backend.controller;

import com.codextask.backend.entity.Project;
import com.codextask.backend.entity.User;
import com.codextask.backend.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    IProjectService projectService;

    @DeleteMapping(value = "/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }

    @GetMapping(value = "/{name}")
    public Project getProjectByName(@PathVariable("name") String name){
        return projectService.getProjectByName(name);
    }
}

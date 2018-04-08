package com.codextask.backend.service;

import com.codextask.backend.entity.Project;
import com.codextask.backend.entity.Task;

import java.util.List;

public interface IProjectService {

    Project getProjectById(Long id);

    void deleteProject(Long id);

    Project getProjectByName(String name);

    void addProject(Project project);

    void addTaskToProject(Project project, Task task);
}

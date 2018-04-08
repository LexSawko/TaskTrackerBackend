package com.codextask.backend.service.impl;

import com.codextask.backend.entity.Project;
import com.codextask.backend.entity.Task;
import com.codextask.backend.repository.ProjectRepository;
import com.codextask.backend.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService{
    @Autowired
    ProjectRepository projectRepository;

//    @Transactional
//    public List<Project> getProjects(){
//        return projectRepository.findAll();
//    }

    @Transactional
    public Project getProjectById(Long id){
        return projectRepository.findOne(id);
    }

    @Transactional
    public void deleteProject(Long id){
        projectRepository.delete(id);
    }

    @Transactional
    public void addProject(Project project){
        projectRepository.save(project);
    }

    @Transactional
    public void addTaskToProject(Project project, Task task){
        List<Task> tasks = project.getTasks();
        tasks.add(task);
        project.setTasks(tasks);
        projectRepository.save(project);
    }

    @Override
    public Project getProjectByName(String name) {
        return projectRepository.getProjectByName(name);
    }
}

package com.codextask.backend.service.impl;


import com.codextask.backend.entity.Task;
import com.codextask.backend.repository.TaskRepository;
import com.codextask.backend.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    TaskRepository taskRepository;

    @Transactional
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @Transactional
    public Task getTaskById(Long id){
        return taskRepository.findOne(id);
    }

    @Transactional
    public void deleteTask(Long id){
        taskRepository.delete(id);
    }

    @Transactional
    public void addTask(Task task){
        taskRepository.save(task);
    }
}

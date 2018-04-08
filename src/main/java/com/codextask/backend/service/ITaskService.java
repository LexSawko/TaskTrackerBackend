package com.codextask.backend.service;

import com.codextask.backend.entity.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getTasks();

    Task getTaskById(Long id);

    void deleteTask(Long id);

    void addTask(Task task);
}

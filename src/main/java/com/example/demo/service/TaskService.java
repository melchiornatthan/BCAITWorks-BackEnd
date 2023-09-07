package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
    

    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);

        if (tasks.isEmpty()) {
            log.info("You have no task");
            return new ArrayList<>();
        } 

        return tasks;
    }
    
    public Task addTask(Task task) {
    return taskRepository.save(task);
}

    public Task updateTask(String id) {
        Optional<Task> task = taskRepository.findById(UUID.fromString(id));
        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setDone(true);
            existingTask.setUpdatedAt(new Date());
            return taskRepository.save(existingTask);
        } else {
            throw new IllegalArgumentException(String.format("Task with id %s not found", id));
        }
    }
}



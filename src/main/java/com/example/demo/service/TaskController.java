package com.example.demo.service;

import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;


import lombok.extern.slf4j.Slf4j;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("")
    public ResponseEntity<List<Task>> getAll() {
        log.info("Get task list...");
        try {
            List<Task> tasks = taskService.getAll();
            if (tasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        try {
           if(task.getTitle() == null || !StringUtils.hasText(task.getTitle())) {
              log.info("to add item to your task list, you must provide a title");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
           return new ResponseEntity<>(taskService.addTask(task), HttpStatus.OK);
    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

    @PutMapping("")
    public ResponseEntity<Task> updateTask(@RequestBody Map<String, String> body) {
        try {
            if(body.get("id") == null ) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(taskService.updateTask(body.get("id")), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

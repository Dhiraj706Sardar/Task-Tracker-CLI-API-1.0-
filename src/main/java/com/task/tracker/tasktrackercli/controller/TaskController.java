package com.task.tracker.tasktrackercli.controller;


import com.task.tracker.tasktrackercli.dto.TaskRequest;
import com.task.tracker.tasktrackercli.dto.TaskResponse;
import com.task.tracker.tasktrackercli.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // Add task
    @PostMapping("/add")
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.addTask(request));
    }

    // Update task
    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id, @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    // Delete task
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Mark as in-progress
    @PutMapping("/mark-in-progress/{id}")
    public ResponseEntity<TaskResponse> markInProgress(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.markInProgress(id));
    }

    // Mark as done
    @PutMapping("/mark-done/{id}")
    public ResponseEntity<TaskResponse> markDone(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.markDone(id));
    }

    // List all
    @GetMapping("/list")
    public ResponseEntity<List<TaskResponse>> listAll() {
        return ResponseEntity.ok(taskService.listAll());
    }

    // List by status: todo, in-progress, done
    @GetMapping("/list/{status}")
    public ResponseEntity<List<TaskResponse>> listByStatus(@PathVariable String status) {
        return ResponseEntity.ok(taskService.listByStatus(status));
    }
}

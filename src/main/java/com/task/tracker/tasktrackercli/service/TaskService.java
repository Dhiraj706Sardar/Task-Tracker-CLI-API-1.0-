package com.task.tracker.tasktrackercli.service;



import com.task.tracker.tasktrackercli.dto.TaskRequest;
import com.task.tracker.tasktrackercli.dto.TaskResponse;
import com.task.tracker.tasktrackercli.model.Task;
import com.task.tracker.tasktrackercli.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public TaskResponse addTask(TaskRequest request) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setDescription(request.getDescription());
        task.setStatus("todo");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        repository.save(task);
        return mapToResponse(task);
    }

    public TaskResponse updateTask(UUID id, TaskRequest request) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setDescription(request.getDescription());
        task.setUpdatedAt(LocalDateTime.now());

        repository.update(task);
        return mapToResponse(task);
    }

    public void deleteTask(UUID id) {
        repository.delete(id);
    }

    public TaskResponse markInProgress(UUID id) {
        return updateStatus(id, "in-progress");
    }

    public TaskResponse markDone(UUID id) {
        return updateStatus(id, "done");
    }

    public List<TaskResponse> listAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> listByStatus(String status) {
        return repository.findAll().stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TaskResponse updateStatus(UUID id, String newStatus) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(newStatus);
        task.setUpdatedAt(LocalDateTime.now());

        repository.update(task);
        return mapToResponse(task);
    }

    private TaskResponse mapToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }
}

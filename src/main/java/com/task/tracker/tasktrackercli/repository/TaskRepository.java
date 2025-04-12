package com.task.tracker.tasktrackercli.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.task.tracker.tasktrackercli.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Repository
public class TaskRepository {

    // Path to the tasks file
    private static final String FILE_PATH = "src/main/resources/tasks.json";

    // ObjectMapper with JavaTimeModule support for LocalDateTime
    private final ObjectMapper objectMapper;

    public TaskRepository() {
        this.objectMapper = new ObjectMapper();
        // Register JavaTimeModule to support Java 8 date/time types
        this.objectMapper.registerModule(new JavaTimeModule());
        // To write LocalDateTime in ISO-8601 format (e.g., "2025-04-12T08:27:41")
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Retrieves all tasks from the file. If the file doesn't exist, returns an empty list.
     *
     * @return List of tasks or empty list if the file doesn't exist or an error occurs.
     */
    public List<Task> findAll() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                log.warn("File {} does not exist. Returning an empty list.", file.getAbsolutePath());
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            log.error("Failed to read tasks from {}: {}", FILE_PATH, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * Saves the provided list of tasks to the file.
     *
     * @param tasks List of tasks to save.
     */
    public void saveAll(List<Task> tasks) {
        if (tasks == null) {
            log.error("Task list is null and cannot be saved.");
            return;
        }
        try {
            File file = new File(FILE_PATH);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
            log.info("Saved {} tasks to file: {}", tasks.size(), file.getAbsolutePath());
        } catch (IOException e) {
            log.error("Failed to write tasks to {}: {}", FILE_PATH, e.getMessage(), e);
        }
    }

    /**
     * Finds a task by its unique identifier.
     *
     * @param id The UUID of the task to find.
     * @return An Optional containing the task if found, otherwise empty.
     */
    public Optional<Task> findById(UUID id) {
        List<Task> tasks = findAll();
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    /**
     * Saves a single task. If the file exists, it appends the task to the list;
     * otherwise, it creates a new list and saves it.
     *
     * @param task The task to save.
     */
    public void save(Task task) {
        List<Task> tasks = findAll();
        tasks.add(task);
        saveAll(tasks);
    }

    /**
     * Updates an existing task by its ID. If the task is not found, no changes are made.
     *
     * @param updatedTask The task with updated details.
     */
    public void update(Task updatedTask) {
        List<Task> tasks = findAll();
        boolean updated = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(updatedTask.getId())) {
                tasks.set(i, updatedTask);
                updated = true;
                break;
            }
        }
        if (updated) {
            saveAll(tasks);
            log.info("Updated task with ID: {}", updatedTask.getId());
        } else {
            log.warn("Task with ID {} not found. Update failed.", updatedTask.getId());
        }
    }

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id The UUID of the task to delete.
     */
    public void delete(UUID id) {
        List<Task> tasks = findAll();
        boolean removed = tasks.removeIf(task -> task.getId().equals(id));
        if (removed) {
            saveAll(tasks);
            log.info("Deleted task with ID: {}", id);
        } else {
            log.warn("Task with ID {} not found. Delete failed.", id);
        }
    }
}
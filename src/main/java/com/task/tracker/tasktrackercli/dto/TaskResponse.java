package com.task.tracker.tasktrackercli.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskResponse {
    private UUID id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


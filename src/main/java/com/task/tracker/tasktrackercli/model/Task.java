package com.task.tracker.tasktrackercli.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private UUID id;
    private String description;
    private String status; // todo, in-progress, done
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

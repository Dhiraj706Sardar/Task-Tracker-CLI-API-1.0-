package com.task.tracker.tasktrackercli.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "Description must not be blank")
    private String description;
}

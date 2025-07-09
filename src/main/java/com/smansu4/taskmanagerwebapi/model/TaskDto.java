package com.smansu4.taskmanagerwebapi.model;

import lombok.Data;

// shared data transfer object to abstract over both backends
@Data
public class TaskDto {
    private String id;
    private String title;
    private String description;
    private boolean completed;
}
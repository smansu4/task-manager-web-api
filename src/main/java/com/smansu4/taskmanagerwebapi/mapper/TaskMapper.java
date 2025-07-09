package com.smansu4.taskmanagerwebapi.mapper;

import com.smansu4.taskmanagerwebapi.model.Task;
import com.smansu4.taskmanagerwebapi.model.TaskDto;
import com.smansu4.taskmanagerwebapi.model.TaskItem;

public class TaskMapper {

    // RDS ↔ DTO
    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(String.valueOf(task.getId()));
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        return dto;
    }

    public static Task taskFromDto(TaskDto dto) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
    }

    // DynamoDB ↔ DTO
    public static TaskDto toDto(TaskItem item) {
        TaskDto dto = new TaskDto();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setDescription(item.getDescription());
        dto.setCompleted(Boolean.TRUE.equals(item.getCompleted()));
        return dto;
    }

    public static TaskItem taskItemFromDto(TaskDto dto) {
        TaskItem item = new TaskItem();
        item.setId(dto.getId());
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setCompleted(dto.isCompleted());
        return item;
    }
}

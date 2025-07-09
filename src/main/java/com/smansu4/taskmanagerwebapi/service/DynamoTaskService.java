package com.smansu4.taskmanagerwebapi.service;

import com.smansu4.taskmanagerwebapi.mapper.TaskMapper;
import com.smansu4.taskmanagerwebapi.model.TaskDto;
import com.smansu4.taskmanagerwebapi.model.TaskItem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Profile("dynamodb")
@RequiredArgsConstructor
public class DynamoTaskService implements TaskService {

    private final DynamoDbTable<TaskItem> taskTable;

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskDto> tasks = new ArrayList<>();
        taskTable.scan().items().forEach(item -> tasks.add(TaskMapper.toDto(item)));
        return tasks;
    }

    @Override
    public TaskDto getTaskById(String id) {
        TaskItem item = taskTable.getItem(r -> r.key(k -> k.partitionValue(id)));
        if (item == null) throw new RuntimeException("Task not found");
        return TaskMapper.toDto(item);
    }

    @Override
    public TaskDto createTask(TaskDto dto) {
        TaskItem item = TaskMapper.taskItemFromDto(dto);
        if (item.getId() == null) item.setId(UUID.randomUUID().toString());
        taskTable.putItem(item);
        return TaskMapper.toDto(item);
    }

    @Override
    public TaskDto updateTask(String id, TaskDto dto) {
        TaskItem existing = taskTable.getItem(r -> r.key(k -> k.partitionValue(id)));
        if (existing == null) throw new RuntimeException("Task not found");

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setCompleted(dto.isCompleted());
        taskTable.putItem(existing);
        return TaskMapper.toDto(existing);
    }

    @Override
    public void deleteTask(String id) {
        taskTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}

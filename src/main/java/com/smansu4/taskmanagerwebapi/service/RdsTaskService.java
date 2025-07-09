package com.smansu4.taskmanagerwebapi.service;

import com.smansu4.taskmanagerwebapi.mapper.TaskMapper;
import com.smansu4.taskmanagerwebapi.model.Task;
import com.smansu4.taskmanagerwebapi.model.TaskDto;
import com.smansu4.taskmanagerwebapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("rds")
@RequiredArgsConstructor
public class RdsTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(String id) {
        Task task = taskRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto dto) {
        Task saved = taskRepository.save(TaskMapper.taskFromDto(dto));
        return TaskMapper.toDto(saved);
    }

    @Override
    public TaskDto updateTask(String id, TaskDto dto) {
        Task existing = taskRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setCompleted(dto.isCompleted());
        return TaskMapper.toDto(taskRepository.save(existing));
    }

    @Override
    public void deleteTask(String id) {
        taskRepository.deleteById(Long.parseLong(id));
    }
}

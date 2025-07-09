package com.smansu4.taskmanagerwebapi.controller;

import com.smansu4.taskmanagerwebapi.model.Task;
import com.smansu4.taskmanagerwebapi.model.TaskDto;
import com.smansu4.taskmanagerwebapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto dto) {
        return taskService.createTask(dto);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable String id, @RequestBody TaskDto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}

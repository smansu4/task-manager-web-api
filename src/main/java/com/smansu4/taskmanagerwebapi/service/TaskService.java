package com.smansu4.taskmanagerwebapi.service;

import com.smansu4.taskmanagerwebapi.model.Task;
import com.smansu4.taskmanagerwebapi.model.TaskDto;
import io.micrometer.observation.ObservationFilter;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(String id);
    TaskDto createTask(TaskDto task);
    TaskDto updateTask(String id, TaskDto task);
    void deleteTask(String id);
}

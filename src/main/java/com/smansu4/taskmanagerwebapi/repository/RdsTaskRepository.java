package com.smansu4.taskmanagerwebapi.repository;

import com.smansu4.taskmanagerwebapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdsTaskRepository extends JpaRepository<Task, Long> {

}

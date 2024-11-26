package com.example.trelloclone.repository;


import com.example.trelloclone.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    // Custom method to find a task by taskId
    Optional<Task> findByTaskId(String taskId);




}


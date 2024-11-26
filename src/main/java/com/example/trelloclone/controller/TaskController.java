package com.example.trelloclone.controller;

import com.example.trelloclone.model.Task;
import com.example.trelloclone.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> modifyTask(
            @PathVariable("taskId") String taskId,
            @RequestBody Task updatedTask) {
        Task modifiedTask = taskService.modifyTask(taskId, updatedTask);
        return ResponseEntity.ok(modifiedTask);
    }

    @PutMapping("/{taskId}/restore")
    public ResponseEntity<Task> restoreTask(@PathVariable("taskId") String taskId) {
        Task restoredTask = taskService.restoreTask(taskId);
        return restoredTask != null ? ResponseEntity.ok(restoredTask) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") String taskId) {
        boolean success = taskService.deleteTask(taskId);
        return success ? ResponseEntity.ok("Task deleted successfully.") :
                ResponseEntity.status(500).body("Failed to delete task.");
    }

    @GetMapping("/board")
    public ResponseEntity<List<Task>> showBoard() {
        List<Task> board = taskService.showBoard();
        return ResponseEntity.ok(board);
    }

    // Endpoint to start a task - changes state to "Doing"
    @PutMapping("/{taskId}/start")
    public ResponseEntity<Task> startTask(@PathVariable("taskId") String taskId) {
        Task task = taskService.startTask(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    // Endpoint to complete a task - changes state to "Done"
    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable("taskId") String taskId) {
        Task task = taskService.completeTask(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    // Endpoint to revert a task to the previous state
    @PutMapping("/{taskId}/revert")
    public ResponseEntity<Task> revertTask(@PathVariable("taskId") String taskId) {
        Task task = taskService.revertTask(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    
}

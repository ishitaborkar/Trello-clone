package com.example.trelloclone.controller;

import com.example.trelloclone.model.User;
import com.example.trelloclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Modify an existing user's details 
    @PutMapping("/{userId}")
    public ResponseEntity<User> modifyUser(
            @PathVariable("userId") String userId,
            @RequestBody User updatedUser) {
        User modifiedUser = userService.modifyUser(userId, updatedUser);
        return modifiedUser != null ? ResponseEntity.ok(modifiedUser) :
                ResponseEntity.status(404).body(null);  // Return 404 if user not found
    }

    // Delete a user by their ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        boolean success = userService.deleteUser(userId);
        return success ? ResponseEntity.ok("User deleted successfully.") :
                ResponseEntity.status(500).body("Failed to delete user.");
    }

    // Get a user by their ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        User user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) :
                ResponseEntity.status(404).body(null);  // Return 404 if user not found
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Assign a task to a user
    @PostMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<String> assignTaskToUser(
            @PathVariable("userId") String userId,
            @PathVariable("taskId") String taskId) {
        boolean success = userService.assignTaskToUser(userId, taskId);
        return success ? ResponseEntity.ok("Task assigned successfully.") :
                ResponseEntity.status(400).body("Failed to assign task.");
    }

    // Remove a task from a user
    @DeleteMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<String> removeTaskFromUser(
            @PathVariable("userId") String userId,
            @PathVariable("taskId") String taskId) {
        boolean success = userService.removeTaskFromUser(userId, taskId);
        return success ? ResponseEntity.ok("Task removed successfully.") :
                ResponseEntity.status(400).body("Failed to remove task.");
    }
}

package com.example.trelloclone.service;

import com.example.trelloclone.model.User;
import com.example.trelloclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Modify an existing user's details
    public User modifyUser(String userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }

    // Delete a user by their ID
    public boolean deleteUser(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

    // Get a user by their ID
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Assign a task to a user
    public boolean assignTaskToUser(String userId, String taskId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.assignTask(taskId);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    // Remove a task from a user
    public boolean removeTaskFromUser(String userId, String taskId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.removeTask(taskId);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}

package com.example.trelloclone.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users") // Specifies the MongoDB collection for users
public class User {

    @Id
    private String id;         // Unique MongoDB identifier for each user document

    private String name;       // User name
    private String email;      // User email

    private List<String> tasksAssigned = new ArrayList<>(); // List of taskIds assigned to the user

    // Constructors
    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTasksAssigned() {
        return tasksAssigned;
    }

    public void assignTask(String taskId) {
        if (!tasksAssigned.contains(taskId)) {
            this.tasksAssigned.add(taskId);
        }
    }

    public void removeTask(String taskId) {
        this.tasksAssigned.remove(taskId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tasksAssigned=" + tasksAssigned +
                '}';
    }
}

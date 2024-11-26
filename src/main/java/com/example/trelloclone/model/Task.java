package com.example.trelloclone.model;

import com.example.trelloclone.memento.TaskMemento;
import com.example.trelloclone.model.state.DoingState;
import com.example.trelloclone.model.state.DoneState;
import com.example.trelloclone.model.state.TaskState;
import com.example.trelloclone.model.state.TodoState;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "tasks") // Specifies the MongoDB collection name
public class Task {

    @Id
    private String id; // MongoDB's unique identifier for each document

    private String taskId;             // Custom task identifier
    private TaskState state;           // Task state object (uses TaskState interface)
    private String assignedTo;         // e.g., "John"
    private String description;        // Task description
    private List<String> comments = new ArrayList<>(); // Comments list

    private LocalDateTime createdAt;   // Task creation timestamp
    private LocalDateTime updatedAt;   // Last update timestamp
    // private TaskState taskState;
    // Constructors
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.state = new TodoState();// Set creation timestamp on initialization
    }

    public Task(String taskId, TaskState state, String assignedTo, String description) {
        this.taskId = taskId;
        this.state = state;
        this.assignedTo = assignedTo;
        this.description = description;
        this.createdAt = LocalDateTime.now(); // Set creation timestamp on initialization
        this.updatedAt = LocalDateTime.now(); // Initialize updated timestamp as well
    }

    // Method to create a Memento
    public TaskMemento saveToMemento() {
        return new TaskMemento(taskId, description, state.getStateName());
    }

    // Method to restore from a Memento
    public void restoreFromMemento(TaskMemento memento) {
        this.taskId = memento.getTaskId();
        this.description = memento.getDescription();
        // You might need to set the state based on the state name
        // Assuming you have a method to get state by name
        this.state = getStateByName(memento.getState());
    }

    // Method to get the state by name
    private TaskState getStateByName(String stateName) {
        switch (stateName) {
            case "Todo":
                return new TodoState();
            case "Doing":
                return new DoingState();
            case "Done":
                return new DoneState();
            default:
                return new TodoState(); // Default case
        }
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Update updatedAt timestamp whenever a modification occurs
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    // Delegate state-specific behavior to the TaskState
    public void transition() {
        state.transition(this);
    }

}

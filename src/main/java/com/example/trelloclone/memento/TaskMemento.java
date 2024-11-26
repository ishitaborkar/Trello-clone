package com.example.trelloclone.memento;



public class TaskMemento {
    private final String taskId;
    private final String description;
    private final String state; // Store the state name

    public TaskMemento(String taskId, String description, String state) {
        this.taskId = taskId;
        this.description = description;
        this.state = state;
    }

    // Getters for the stored state
    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }
}

package com.example.trelloclone.model.state;


import com.example.trelloclone.model.Task;

public class DoneState implements TaskState {

    @Override
    public void transition(Task task) {
        // No further transitions from Done; could be an exception or just do nothing
        System.out.println("Task is already in Done state.");
    }

    @Override
    public String getStateName() {
        return "Done";
    }
}


package com.example.trelloclone.model.state;

import com.example.trelloclone.model.Task;

public class TodoState implements TaskState {

    @Override
    public void transition(Task task) {
        // Transition logic (e.g., from Todo to Doing)
        task.setState(new DoingState()); // Transition to DoingState
    }

    @Override
    public String getStateName() {
        return "Todo";
    }
}
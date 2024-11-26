package com.example.trelloclone.model.state;

import com.example.trelloclone.model.Task;

public class DoingState implements TaskState {

    @Override
    public void transition(Task task) {
        // Transition logic (e.g., from Doing to Done)

        task.setState(new DoneState());
    }

    @Override
    public String getStateName() {
        return "Doing";
    }
}
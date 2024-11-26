package com.example.trelloclone.model.state;


import com.example.trelloclone.model.Task;

public interface TaskState {
    void transition(Task task);
    String getStateName();
}

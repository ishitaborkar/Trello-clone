package com.example.trelloclone.memento;

import com.example.trelloclone.model.Task;
import java.util.Stack;


public class TaskCaretaker {
    private Stack<TaskMemento> mementoStack = new Stack<>();

    // Save the current state
    public void saveState(Task task) {
        mementoStack.push(task.saveToMemento());
    }

    // Restore the last saved state
    public void restoreState(Task task) {
        if (!mementoStack.isEmpty()) {
            TaskMemento memento = mementoStack.pop();
            task.restoreFromMemento(memento);
        } else {
            System.out.println("No states to restore.");
        }
    }
}



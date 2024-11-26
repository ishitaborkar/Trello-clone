package com.example.trelloclone.service;

import com.example.trelloclone.memento.TaskCaretaker;
import com.example.trelloclone.model.Task;
import com.example.trelloclone.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCaretaker taskCaretaker;


    // Create a new task with default Todo state
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    // Modify an existing task's details (excluding state)
    public Task modifyTask(String taskId, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findByTaskId(taskId);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            // Save the current state before modifying
            taskCaretaker.saveState(existingTask);
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setAssignedTo(updatedTask.getAssignedTo());
            existingTask.setComments(updatedTask.getComments());
            existingTask.updateTimestamp();
            return taskRepository.save(existingTask);
        }
        return null;
    }

        // Restore the last saved state of a task
        public Task restoreTask(String taskId) {
            Optional<Task> existingTaskOptional = taskRepository.findByTaskId(taskId);
            if (existingTaskOptional.isPresent()) {
                Task existingTask = existingTaskOptional.get();
                taskCaretaker.restoreState(existingTask); // Restore the last saved state
                return taskRepository.save(existingTask); // Save the restored state back to the repository
            }
            return null;
        }

    // Start task - transitions from Todo to Doing
    public Task startTask(String taskId) {
        Optional<Task> taskOptional = taskRepository.findByTaskId(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.transition();  // Calls the transition() method through Task's state
            task.updateTimestamp();
            return taskRepository.save(task);
        }
        return null;
    }

    // Complete task - transitions from Doing to Done
    public Task completeTask(String taskId) {
        Optional<Task> taskOptional = taskRepository.findByTaskId(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.transition();  // Calls the transition() method through Task's state
            task.updateTimestamp();
            return taskRepository.save(task);
        }
        return null;
    }

    // Revert task - transitions from Done to Doing or from Doing to Todo
    public Task revertTask(String taskId) {
        Optional<Task> taskOptional = taskRepository.findByTaskId(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.transition();  // Calls the transition() method through Task's state
            task.updateTimestamp();
            return taskRepository.save(task);
        }
        return null;
    }

    // Delete a task by its taskId
    public boolean deleteTask(String taskId) {
        Optional<Task> taskOptional = taskRepository.findByTaskId(taskId);
        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
            return true;
        }
        return false;
    }

    // Show all tasks on the board
    public List<Task> showBoard() {
        return taskRepository.findAll();
    }

}

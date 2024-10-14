package com.example.decs.Service;

import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;
import com.example.decs.Repository.Implementation.TaskRepositoryImpl;
import com.example.decs.Repository.TaskRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class TaskService {


    private TaskRepository taskRepo;

    public TaskService() {
        this.taskRepo = new TaskRepositoryImpl();
    }

    public Task createTask(Task task) {
        return taskRepo.save(task); // Persist the task entity
    }

    public Task updateTask(Task updatedTask, long id) {
        Optional<Task> existingTask = Optional.ofNullable(taskRepo.getTaskbyId(id)); // Fetch the task by ID

        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());

            return taskRepo.update(task); // Update the task in the database
        } else {
            // You could throw an exception or handle the case where the task is not found
            throw new IllegalArgumentException("Task with ID " + id + " not found.");
        }
    }

    public List<Task> findByUser(User user){
        return taskRepo.findbyUser(user);
    }
    public List<Task> findall(){
        return taskRepo.findAll();
    }

    public Task findById(long id){
        return taskRepo.getTaskbyId(id);
    }

    public boolean assignto(User user,Task task){
        try {
            taskRepo.assignTo(user, task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void change_status(Task task,int choix){
        taskRepo.change_status(task,choix);
    }

}


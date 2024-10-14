package com.example.devsync.Service;

import com.example.devsync.Repository.TaskRepository;

public class TaskService {
    private final TaskRepository taskreop;

    public TaskService() {
        this.taskreop = new TaskRepository();
    }
}

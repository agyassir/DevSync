package com.example.decs.Repository;

import com.example.decs.Entity.Tag;
import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;

import java.util.List;

public interface TaskRepository {
     Task update(Task task);
     Task save(Task task);
     List<Task> findByTag (Tag tag);
    List<Task> findAll();
    List<Task> findbyUser(User user);
    void assignTo(User user,Task task);
    Task getTaskbyId(long id);
    void delete(long id);
    void change_status(Task task,int choix);

}

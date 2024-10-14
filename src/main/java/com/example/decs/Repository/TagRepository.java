package com.example.decs.Repository;

import com.example.decs.Entity.Tag;
import com.example.decs.Entity.Task;

import java.util.List;

public interface TagRepository {
    Tag update(Tag task);
    Tag save(Tag task);
    List<Tag> findTagsByTask(Task task);
    List<Tag> findAll();
    Tag getTaskbyId(long id);
    void delete(long id);

}

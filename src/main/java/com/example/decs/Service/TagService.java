package com.example.decs.Service;

import com.example.decs.Entity.Tag;
import com.example.decs.Entity.Task;
import com.example.decs.Repository.Implementation.TagRepositoryImpl;
import com.example.decs.Repository.TagRepository;

import java.util.List;

public class TagService {
    private TagRepository tagRepository=new TagRepositoryImpl();

     public Tag createTag(Tag tag) {
            return tagRepository.save(tag);
        }

        public Tag updateTag(Tag tag) {
            return tagRepository.update(tag);
        }

        public List<Tag> getAllTags() {
            return tagRepository.findAll();
        }

        public List<Tag> findTagsByTask(Task task) {
            return tagRepository.findTagsByTask(task);
        }

        public Tag getTagById(long id) {
            return tagRepository.getTaskbyId(id);
        }

        public void deleteTag(long id) {
            tagRepository.delete(id);
        }
    }



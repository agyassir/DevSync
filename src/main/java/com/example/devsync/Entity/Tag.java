package com.example.devsync.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
    public class Tag {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Task> tasks;
    }

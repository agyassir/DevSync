package com.example.devsync.Entity;

import com.example.devsync.Entity.Enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private boolean isCompleted;
    @ManyToOne
    private User assignedTo;
}

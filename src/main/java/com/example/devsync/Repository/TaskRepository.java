package com.example.devsync.Repository;

import com.example.devsync.Entity.Tag;
import com.example.devsync.Entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TaskRepository {
    private final EntityManagerFactory entityManagerFactory;

    public TaskRepository() {
        this.entityManagerFactory =  Persistence.createEntityManagerFactory("my-persistence-unit");;
    }
    public void save(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Task> findByTag (Tag tag){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t JOIN t.tags tg WHERE tg = :tag", Task.class)
                .setParameter("tag",tag)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }

    public List<Task> findAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }

    public Task getTaskbyId(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task tasks = entityManager.createQuery("SELECT t FROM Task t  WHERE t.id = :id", Task.class)
                .setParameter("id",id)
                .getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }

    public void update(Task task,long taskId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task task1=entityManager.find(Task.class,taskId);
        if (task1!=null){
            task1.setTitle(task.getTitle());
            task1.setDescription(task.getDescription());
            task1.setAssignedTo(task.getAssignedTo());
            task1.setStatus(task.getStatus());
            task1.setDueDate(task.getDueDate());
            task1.setStartDate(task.getStartDate());
            task1.setEndDate(task.getEndDate());
            entityManager.merge(task1);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Task task1=entityManager.find(Task.class,id);
        if (task1!=null){
            entityManager.remove(task1);
        }
        entityManager.getTransaction().commit();
        entityManager.close();

    }


}

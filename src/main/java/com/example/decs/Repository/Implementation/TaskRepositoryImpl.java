package com.example.decs.Repository.Implementation;

import com.example.decs.Entity.Enums.Status;
import com.example.decs.Entity.Tag;
import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;
import com.example.decs.Repository.TaskRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {


    private EntityManagerFactory emf;

    public TaskRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("brief");
    }

    @Override
    public Task save(Task task) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println(task.toString());
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
        return task;
    }
@Override
    public List<Task> findByTag (Tag tag){
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t JOIN t.tags tg WHERE tg = :tag", Task.class)
                .setParameter("tag",tag)
                .getResultList();
                entityManager.getTransaction().commit();
                entityManager.close();
        return tasks;
    }
@Override
    public List<Task> findAll(){

    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return tasks;
    }
@Override
    public Task getTaskbyId(long id){
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
        Task tasks = entityManager.createQuery("SELECT t FROM Task t  WHERE t.id = :id", Task.class)
                .setParameter("id",id)
                .getSingleResult();
                entityManager.getTransaction().commit();
                entityManager.close();
        return tasks;
    }
@Override
    public Task update(Task task){
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
        Task task1=entityManager.find(Task.class,task.getId());
        entityManager.merge(task1);
        entityManager.getTransaction().commit();
        entityManager.close();
        return getTaskbyId(task.getId());
    }
@Override
    public void delete(long id){
    EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
        Task task1=entityManager.find(Task.class,id);
        if (task1!=null){
            entityManager.remove(task1);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Task> findbyUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t  WHERE t.assignedTo = :User", Task.class)
                .setParameter("User",user)
                .getResultList();
                entityManager.getTransaction().commit();
                entityManager.close();
        return tasks;
    }

    @Override
    public void assignTo(User user, Task task) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();


            Task existingTask = entityManager.find(Task.class, task.getId());
            if (existingTask != null) {
                existingTask.setAssignedTo(user);
                entityManager.merge(existingTask);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void change_status(Task task, int choix) {

        switch (choix) {
            case 1:
                task.setStatus(Status.AFAIRE);  // To Do
                break;
            case 2:
                task.setStatus(Status.REFUSER);  // In Progress
                break;
            case 3:
                task.setStatus(Status.EN_COURS);  // In Progress
                break;
            case 4:
                task.setStatus(Status.TERMINE);  // In Progress
                break;
            default:
                throw new IllegalArgumentException("Invalid status choice");
        }

        // Assuming the repository has a method to save or update the task
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(task);  // Update the task in the database
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}

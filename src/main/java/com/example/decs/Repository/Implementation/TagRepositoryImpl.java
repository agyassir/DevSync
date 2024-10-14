package com.example.decs.Repository.Implementation;

import com.example.decs.Entity.Tag;
import com.example.decs.Entity.Task;
import com.example.decs.Repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TagRepositoryImpl implements TagRepository {
    private EntityManagerFactory emf;

    public TagRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("brief");
    }
    @Override
    public Tag update(Tag tag) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Tag updatedTag = em.merge(tag); // Merges the state of the tag entity into the current persistence context
            em.getTransaction().commit();
            return updatedTag;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Tag save(Tag tag) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tag); // Persists the tag entity in the database
            em.getTransaction().commit();
            return tag;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tag> findTagsByTask(Task task) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tag t JOIN t.tasks task WHERE task = :task", Tag.class)
                    .setParameter("task", task)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tag> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tag t", Tag.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Tag getTaskbyId(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Tag.class, id); // Retrieves a Task entity by its ID
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Tag tag = em.find(Tag.class, id); // Finds the Tag entity to be deleted
            if (tag != null) {
                em.remove(tag); // Removes the entity from the database
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

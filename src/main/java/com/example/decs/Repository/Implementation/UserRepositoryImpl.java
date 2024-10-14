package com.example.decs.Repository.Implementation;

import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;
import com.example.decs.Repository.UserRepository;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
   private EntityManagerFactory emf;

    public UserRepositoryImpl() {
        this.emf = Persistence.createEntityManagerFactory("brief");
    }

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            User user = query.getSingleResult();
            entityManager.getTransaction().commit();
            return user;
        } catch (NoResultException e) {
            return null; // Handle no result case
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        EntityManager entityManager = emf.createEntityManager();
        User user = null;

        try {
            entityManager.getTransaction().begin();
            user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            // No user found, return empty Optional
            entityManager.getTransaction().rollback(); // Rollback transaction if needed
            return Optional.empty();
        } catch (Exception e) {
            // Handle other exceptions as needed
            entityManager.getTransaction().rollback();
            throw e; // Or handle it accordingly
        } finally {
            entityManager.close();
        }

        return Optional.ofNullable(user); // Wrap the result in Optional
    }


    @Override
    public User save(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("SELECT t FROM User t", User.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }
    @Override
    public User getById(long id) {
        EntityManager entityManager = emf.createEntityManager();
        User user = null;

        try {
            // Retrieve the user from the database using find
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return user;
    }

    @Override
    public void MinusCoins(User usera, int count) {
        // Fetch the user from the database
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, usera.getId());

        if (user != null) {
            // Ensure the user has enough coins to subtract
            if (user.getCoins() >= count) {
                user.setCoins(user.getCoins() - count);

                // Persist the updated user entity
                entityManager.merge(user);
            } else {
                throw new IllegalArgumentException("User does not have enough coins");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);  // Merging the detached user entity to persist changes
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();  // Rollback in case of failure
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}

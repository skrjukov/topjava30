package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        }
        return get(meal.id(), userId) == null ? null : em.merge(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        User ref = em.getReference(User.class, userId);
        Meal meal = em.find(Meal.class, id);
        meal.setUser(ref);
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
//        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
//                .getResultList().stream().filter(m -> m.getUser().getId() == userId)
//                .collect(Collectors.toList());

        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
//        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
//                .getResultList().stream()
//                .filter(m -> Util.isBetweenHalfOpen(m.getDateTime(),startDateTime,endDateTime))
//                .collect(Collectors.toList());

                return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                                .setParameter("userId", userId)
                                .setParameter("startDateTime", startDateTime)
                                .setParameter("endDateTime", endDateTime)
                                .getResultList();
    }
}
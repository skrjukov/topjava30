package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(@Param("userId") int userId);
    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId and m.dateTime>:startDate and " +
            "m.dateTime<:endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetweenHalfOpen(@Param("userId") int userId, @Param("startDate")LocalDateTime startDate,
                                  @Param("endDate")LocalDateTime endDate);
}
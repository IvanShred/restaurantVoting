package ru.project.restaurantVoting.repository.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override
    Optional<Meal> findById(Integer id);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.dateMeal=:date ORDER BY m.price")
    List<Meal> findAll(@Param("date") LocalDate date);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restaurantId AND m.dateMeal=:date ORDER BY m.price")
    List<Meal> findAllByRestaurant(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);
}

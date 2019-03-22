package ru.project.restaurantVoting.repositoty.meal;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;

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
    @Query("SELECT m FROM Meal m WHERE m.dateMeal=:currentDate")
    List<Meal> findAll(@Param("currentDate") LocalDate date);

    @Modifying
    @Query("SELECT m FROM Meal m WHERE m.restaurant=:restaurant AND m.dateMeal=:currentDate")
    List<Meal> findAllByRestaurant(@Param("restaurant") Restaurant restaurant , @Param("currentDate") LocalDate date);
}

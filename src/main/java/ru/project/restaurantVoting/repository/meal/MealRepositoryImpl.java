package ru.project.restaurantVoting.repository.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudRepository.save(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Meal> getAll(LocalDate date) {
        return crudRepository.findAll(date);
    }

    @Override
    public List<Meal> getAllByRestaurant(int restaurantId, LocalDate date) {
        return crudRepository.findAllByRestaurant(restaurantId, date);
    }
}

package ru.project.restaurantVoting.repository.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.util.DateUtil;

import java.util.List;

@Repository
public class MealRepositoryImpl implements MealRepository {
    //private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudMealRepository crudRepository;

    @Override
    @Transactional
    public Meal save(Meal meal) {
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
    public List<Meal> getAll() {
        return crudRepository.findAll(DateUtil.getCurrentDate());
    }

    @Override
    public List<Meal> getAllByRestaurant(Restaurant restaurant) {
        return crudRepository.findAllByRestaurant(restaurant, DateUtil.getCurrentDate());
    }
}

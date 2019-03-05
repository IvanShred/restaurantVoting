package ru.project.restaurantVoting.repositoty;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MealRepositoryImpl implements MealRepository {

    @Override
    @Transactional
    public Meal save(Meal meal) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Meal get(int id) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }

    @Override
    public List<Meal> getAllByRestaurant(Restaurant restaurant) {
        return null;
    }
}

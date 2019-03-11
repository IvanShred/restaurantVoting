package ru.project.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.repositoty.meal.MealRepository;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealServiceImpl implements MealService{

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public List<Meal> getAllByRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<Meal> getAll() {
        return null;
    }

    @Override
    public void update(Meal meal) {

    }

    @Override
    public Meal create(Meal meal) {
        return null;
    }
}

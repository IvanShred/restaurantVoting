package ru.project.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.repository.meal.MealRepository;
import ru.project.restaurantVoting.to.MealTo;
import ru.project.restaurantVoting.to.MealsRestaurant;
import ru.project.restaurantVoting.util.DateUtil;
import ru.project.restaurantVoting.util.MealsUtil;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.project.restaurantVoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService{

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Meal> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId, DateUtil.getCurrentDate());
    }

    @Override
    public List<MealsRestaurant> getAll() {
        return MealsUtil.getAllForRestaurants(repository.getAll(DateUtil.getCurrentDate()));
    }

    @Override
    public void update(MealTo mealTo) {
        Meal meal = MealsUtil.updateFromTo(get(mealTo.getId()), mealTo);
        repository.save(meal);
    }

    @Override
    public Meal create(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }
}

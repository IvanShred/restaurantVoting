package ru.project.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.repository.meal.MealRepository;
import ru.project.restaurantVoting.to.MealTo;
import ru.project.restaurantVoting.to.MealsRestaurantTo;
import ru.project.restaurantVoting.util.DateUtil;
import ru.project.restaurantVoting.util.MealsUtil;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import java.util.List;

import static ru.project.restaurantVoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Meal> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId, DateUtil.getCurrentDate());
    }

    @Cacheable("menu")
    @Override
    public List<MealsRestaurantTo> getMenu() {
        return MealsUtil.getAllForRestaurants(repository.getAll(DateUtil.getCurrentDate()));
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        if (meal.getRestaurant() != null) {
            repository.save(meal, meal.getRestaurant().getId());
        }
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void update(MealTo mealTo) {
        Meal meal = MealsUtil.updateFromTo(get(mealTo.getId()), mealTo);
        repository.save(meal, mealTo.getRestaurantId());
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restaurantId);
    }
}

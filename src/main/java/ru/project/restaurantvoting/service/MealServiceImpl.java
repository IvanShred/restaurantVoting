package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.repository.meal.MealRepository;
import ru.project.restaurantvoting.to.responseTo.MealResponseTo;
import ru.project.restaurantvoting.to.MealTo;
import ru.project.restaurantvoting.util.MealsUtil;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public MealResponseTo get(int id) throws NotFoundException {
        return MealsUtil.convertToResponse(checkNotFoundWithId(repository.get(id), id));
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Meal> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId, LocalDate.now());
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        if (meal.getRestaurant() != null) {
            repository.save(meal, meal.getMealType().getId(), meal.getRestaurant().getId());
        }
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void update(MealTo mealTo) {
        int mealId = mealTo.getId();
        Meal meal = MealsUtil.updateFromTo(checkNotFoundWithId(repository.get(mealId), mealId), mealTo);
        repository.save(meal, mealTo.getMealTypeId(), mealTo.getRestaurantId());
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    @Transactional
    public MealResponseTo create(Meal meal, int mealTypeId, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return MealsUtil.convertToResponse(repository.save(meal, mealTypeId, restaurantId));
    }
}

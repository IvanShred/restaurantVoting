package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.repository.meal.CrudMealRepository;
import ru.project.restaurantvoting.repository.mealType.CrudMealTypeRepository;
import ru.project.restaurantvoting.repository.restaurant.CrudRestaurantRepository;
import ru.project.restaurantvoting.to.MealTo;
import ru.project.restaurantvoting.to.responseTo.MealResponseTo;
import ru.project.restaurantvoting.util.MealsUtil;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private final CrudMealRepository crudMealRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    private final CrudMealTypeRepository crudMealTypeRepository;

    @Autowired
    public MealServiceImpl(CrudMealRepository crudMealRepository, CrudRestaurantRepository crudRestaurantRepository,
                           CrudMealTypeRepository crudMealTypeRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMealTypeRepository = crudMealTypeRepository;
    }

    @Override
    public MealResponseTo get(int id) throws NotFoundException {
        Meal meal = crudMealRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found entity with id=%d", id)));

        return MealsUtil.convertToResponse(meal);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(crudMealRepository.delete(id) != 0, id);
    }

    @Override
    public List<Meal> getAllByRestaurant(int restaurantId) {
        return crudMealRepository.findAllByRestaurant(restaurantId, LocalDate.now());
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void update(Meal meal) {
        Assert.notNull(meal, "meal must not be null");

        if (meal.getRestaurant() != null && meal.getMealType() != null) {
            if (!meal.isNew() && get(meal.getId()) == null) {
                return;
            }

            Meal mealToSave = prepareMealToSave(meal, meal.getMealType().getId(), meal.getRestaurant().getId());

            crudMealRepository.save(mealToSave);
        }
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    public void update(MealTo mealTo) {
        int mealId = mealTo.getId();

        Meal meal = MealsUtil.updateFromTo(crudMealRepository.findById(mealId)
                .orElseThrow(() -> new NotFoundException(String.format("Not found entity with id=%d", mealId))), mealTo);

        Meal mealToSave = prepareMealToSave(meal, mealTo.getMealTypeId(), mealTo.getRestaurantId());

        crudMealRepository.save(mealToSave);
    }

    @CacheEvict(value = "menu", allEntries = true)
    @Override
    @Transactional
    public MealResponseTo create(Meal meal, int mealTypeId, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");

        Meal mealToSave = prepareMealToSave(meal, mealTypeId, restaurantId);

        return MealsUtil.convertToResponse(crudMealRepository.save(mealToSave));
    }

    private Meal prepareMealToSave(Meal meal, int mealTypeId, int restaurantId){
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        meal.setMealType(crudMealTypeRepository.getOne(mealTypeId));
        return meal;
    }
}

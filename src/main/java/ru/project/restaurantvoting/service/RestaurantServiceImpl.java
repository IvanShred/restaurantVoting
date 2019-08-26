package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.repository.restaurant.CrudRestaurantRepository;
import ru.project.restaurantvoting.to.RestaurantTo;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.project.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final CrudRestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @CacheEvict(value = {"menu", "restaurants"}, allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @CacheEvict(value = {"menu", "restaurants"}, allEntries = true)
    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        int id = restaurant.getId();

        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Not found entity with id = %s", id));
        }
        repository.save(restaurant);
    }

    @CacheEvict(value = {"menu", "restaurants"}, allEntries = true)
    @Override
    public void update(RestaurantTo restaurantTo, int restaurantId) {
        assureIdConsistent(restaurantTo, restaurantId);
        Restaurant restaurant = new Restaurant(restaurantId, restaurantTo.getName(), restaurantTo.getAddress());
        repository.save(restaurant);
    }

    @CacheEvict(value = {"menu", "restaurants"}, allEntries = true)
    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");

        return repository.save(restaurant);
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return checkNotFoundWithId(repository.getWithMeals(id), id);
    }

    @Cacheable("menu")
    @Override
    public List<Restaurant> getMenu(LocalDate date) {

        return repository.getAllWithMeals(date != null ? date : LocalDate.now());
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}

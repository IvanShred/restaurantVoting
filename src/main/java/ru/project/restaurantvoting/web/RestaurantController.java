package ru.project.restaurantvoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.project.restaurantvoting.model.Restaurant;
import ru.project.restaurantvoting.service.MealService;
import ru.project.restaurantvoting.service.RestaurantService;
import ru.project.restaurantvoting.to.MealsRestaurantTo;
import ru.project.restaurantvoting.to.RestaurantTo;
import ru.project.restaurantvoting.util.RestaurantUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService service;

    @Autowired
    private MealService mealService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        Restaurant created = service.create(RestaurantUtil.createNewFromTo(restaurantTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(@RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("update {} with id={}", restaurantTo, id);
        service.update(restaurantTo, id);
    }

    @GetMapping("/menu")
    public List<MealsRestaurantTo> getAll() {
        log.info("getMenu");
        return mealService.getMenu();
    }
}

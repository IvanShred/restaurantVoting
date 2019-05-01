package ru.project.restaurantVoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.project.restaurantVoting.model.Meal;
import ru.project.restaurantVoting.service.MealService;
import ru.project.restaurantVoting.to.MealTo;
import ru.project.restaurantVoting.to.MealsRestaurant;
import ru.project.restaurantVoting.util.MealsUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String REST_URL = "/rest/meals";

    @Autowired
    private MealService service;

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody MealTo mealTo) {
        Meal created = service.create(MealsUtil.createNewFromTo(mealTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        //assureIdConsistent(meal, id);
        service.update(meal);
    }

    @GetMapping("/by")
    public List<Meal> getByRestaurant(@RequestParam int restaurantId) {
        return service.getAllByRestaurant(restaurantId);
    }
}

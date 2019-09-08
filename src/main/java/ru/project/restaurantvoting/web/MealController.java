package ru.project.restaurantvoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.project.restaurantvoting.model.Meal;
import ru.project.restaurantvoting.service.MealService;
import ru.project.restaurantvoting.to.response.MealResponseTo;
import ru.project.restaurantvoting.to.MealTo;
import ru.project.restaurantvoting.util.MealsUtil;

import java.net.URI;
import java.util.List;

import static ru.project.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.project.restaurantvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/rest/meals";

    @Autowired
    private MealService service;

    @GetMapping("/{id}")
    public MealResponseTo get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MealResponseTo> create(@RequestBody MealTo mealTo) {
        log.info("create {}", mealTo);
        checkNew(mealTo);
        MealResponseTo created = service.create(MealsUtil.createNewFromTo(mealTo), mealTo.getMealTypeId(), mealTo.getRestaurantId());
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
    public void update(@RequestBody MealTo mealTo, @PathVariable int id) {
        log.info("update {} with id={}", mealTo, id);
        assureIdConsistent(mealTo, id);
        service.update(mealTo);
    }

    @GetMapping("/by")
    public List<Meal> getByRestaurantId(@RequestParam int restaurantId) {
        log.info("getByRestaurantId {}", restaurantId);
        return service.getAllByRestaurant(restaurantId);
    }
}

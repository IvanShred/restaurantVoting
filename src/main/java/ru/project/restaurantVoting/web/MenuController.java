package ru.project.restaurantVoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.restaurantVoting.service.MealService;
import ru.project.restaurantVoting.to.MealsRestaurantTo;

import java.util.List;


@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String REST_URL = "/rest/menu";

    @Autowired
    private MealService service;

    @GetMapping
    public List<MealsRestaurantTo> getAll() {
        log.info("getAll");
        return service.getAll();
    }
}

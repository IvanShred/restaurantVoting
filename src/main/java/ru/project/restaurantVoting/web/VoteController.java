package ru.project.restaurantVoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.service.VoteService;
import ru.project.restaurantVoting.to.MealsRestaurant;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService service;

//    @GetMapping
//    public List<MealsRestaurant> getAll() {
//        return service.getMenu();
//    }

    @PutMapping(value = "/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void /*ResponseEntity<Vote>*/ voteForRestaurant(@PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        /*Vote created = */service.vote(restaurantId, userId);

//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{restaurantId}")
//                .buildAndExpand(created.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        int userId = SecurityUtil.authUserId();
        service.cancelVote(userId);
    }
}

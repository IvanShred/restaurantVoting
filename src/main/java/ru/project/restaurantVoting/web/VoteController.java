package ru.project.restaurantVoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.project.restaurantVoting.AuthorizedUser;
import ru.project.restaurantVoting.service.VoteService;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService service;

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void voteForRestaurant(@PathVariable int restaurantId, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("vote {}", restaurantId);
        int userId = authUser.getId();
        service.vote(restaurantId, userId);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("delete vote {}", userId);
        service.cancelVote(userId);
    }
}

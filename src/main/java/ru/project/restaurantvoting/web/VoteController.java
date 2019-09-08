package ru.project.restaurantvoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.restaurantvoting.AuthorizedUser;
import ru.project.restaurantvoting.service.VoteService;
import ru.project.restaurantvoting.to.response.VoteResponseTo;

import java.time.LocalDate;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService service;

    @GetMapping
    public VoteResponseTo getVote(@RequestParam(required = false) LocalDate date,
                                  @AuthenticationPrincipal AuthorizedUser authUser) {
        int userId = authUser.getId();
        log.info("get vote {}", userId);

        return service.getByUserId(authUser.getId(), date);
    }
}

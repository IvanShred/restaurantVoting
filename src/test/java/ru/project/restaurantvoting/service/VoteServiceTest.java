package ru.project.restaurantvoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.restaurantvoting.to.response.VoteResponseTo;
import ru.project.restaurantvoting.util.exception.ChangeVoteException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static ru.project.restaurantvoting.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    void vote() {
        VoteResponseTo vote = service.vote(LocalTime.of(10, 59), RESTAURANT_ID, ADMIN_ID);

        assertNotNull(vote);
        assertEquals(RESTAURANT_ID, vote.getRestaurantId());
        assertEquals(ADMIN_ID, vote.getUserId());
    }

    @Test
    void voteAfterTime() {
        assertThrows(ChangeVoteException.class, () ->
                service.vote(LocalTime.of(11, 1), RESTAURANT_ID, ADMIN_ID));

    }


    @Test
    void getByUserId() {
        VoteResponseTo vote = service.getByUserId(USER_ID, LocalDate.now());

        assertNotNull(vote);
        assertEquals(USER_ID, vote.getUserId());
        assertEquals(LocalDate.now(), vote.getDateVote());
    }
}
package ru.project.restaurantVoting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.util.DateUtil;
import ru.project.restaurantVoting.util.exception.ChangeVoteException;
import ru.project.restaurantVoting.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.project.restaurantVoting.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    void vote() {
        Vote vote = service.vote(RESTAURANT_ID, ADMIN_ID);
        assertMatch(service.getByUserId(ADMIN_ID), vote);
    }

    @Test
    void cancelVote() {
        service.cancelVote(USER_ID);
        assertThrows(NotFoundException.class, () ->
                service.getByUserId(USER_ID));
    }

    @Test
    void reVote() {
        if (DateUtil.TIME_TO_REVOTE < DateUtil.getCurrentTime()) {
            assertThrows(ChangeVoteException.class, () ->
                    service.vote(RESTAURANT_ID, USER_ID));
        } else {
            Vote vote = service.vote(RESTAURANT_ID, USER_ID);
            assertMatch(service.getByUserId(USER_ID), vote);
        }
    }
}
package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.model.Vote;

public interface VoteService {

    Vote getByUserId(int userId);

    Vote vote(int restaurantId, int userId);

    void cancelVote(int userId);
}

package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Vote;

public interface VoteService {

    Vote getByUserId(int userId);

    Vote vote(int restaurantId, int userId);

    void cancelVote(int userId);
}

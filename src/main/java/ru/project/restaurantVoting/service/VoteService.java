package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Vote;

import java.util.Optional;

public interface VoteService {
    Vote create(Vote vote, int userId);

    Vote update(Vote vote, int userId);

    boolean delete(int id, int userId);

    Optional<Vote> getById(int id, int userId);

    Optional<Vote> getByUserId(int userId);
}

package ru.project.restaurantVoting.repository.vote;

import ru.project.restaurantVoting.model.Vote;

import java.util.Optional;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if vote do not belong to userId
    Optional<Vote> getById(int id, int userId);

    Optional<Vote> getByUserId(int userId);
}

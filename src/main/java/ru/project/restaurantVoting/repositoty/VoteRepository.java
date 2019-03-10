package ru.project.restaurantVoting.repositoty;

import ru.project.restaurantVoting.model.Vote;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(Vote meal, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if vote do not belong to userId
    Vote getById(int id, int userId);


}

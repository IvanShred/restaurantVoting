package ru.project.restaurantVoting.repository.vote;

import ru.project.restaurantVoting.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(LocalDate date, int userId, int restaurantId);

    boolean delete(int userId, LocalDate date);

    // null if vote do not belong to userId
    //Optional<Vote> getById(int id, int userId);

    Vote getByUserId(int userId, LocalDate date);
}

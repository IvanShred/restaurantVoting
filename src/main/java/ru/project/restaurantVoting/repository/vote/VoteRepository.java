package ru.project.restaurantVoting.repository.vote;

import ru.project.restaurantVoting.model.Vote;

import java.time.LocalDate;

public interface VoteRepository {

    Vote save(LocalDate date, int userId, int restaurantId);

    boolean delete(int userId, LocalDate date);

    Vote getByUserId(int userId, LocalDate date);
}

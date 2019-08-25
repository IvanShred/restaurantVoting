package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.model.Vote;
import ru.project.restaurantvoting.to.VoteResponseTo;

import java.time.LocalDate;

public interface VoteService {

    Vote getByUserId(int userId);

    VoteResponseTo vote(LocalDate date, int restaurantId, int userId);

    void cancelVote(int userId);
}

package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.to.VoteResponseTo;

import java.time.LocalDate;

public interface VoteService {

    VoteResponseTo getByUserId(int userId, LocalDate date);

    VoteResponseTo vote(LocalDate date, int restaurantId, int userId);
}

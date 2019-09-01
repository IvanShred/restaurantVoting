package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.to.responseTo.VoteResponseTo;

import java.time.LocalDate;
import java.time.LocalTime;

public interface VoteService {

    VoteResponseTo getByUserId(int userId, LocalDate date);

    VoteResponseTo vote(LocalTime time, int restaurantId, int userId);
}

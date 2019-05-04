package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.to.MealsRestaurantTo;

import java.util.List;
import java.util.Optional;

public interface VoteService {
//    Vote create(Vote vote, int userId);
//
//    Vote update(Vote vote, int userId);
//
//    void delete(int id, int userId);
//
//    Optional<Vote> getById(int id, int userId);
//
    Vote getByUserId(int userId);

//    List<MealsRestaurantTo> getMenu();

    Vote vote(int restaurantId, int userId);

    void cancelVote(int userId);
}

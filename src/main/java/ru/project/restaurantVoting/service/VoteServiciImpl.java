package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.util.DateUtil;

import java.util.Optional;

public class VoteServiciImpl implements VoteService {
    @Override
    public Vote create(Vote vote, int userId) {
        return null;
    }

    @Override
    public Vote update(Vote vote, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Optional<Vote> getById(int id, int userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Vote> getByUserId(int userId) {
        return Optional.empty();
    }

    private boolean isExcessTime() {
        return DateUtil.getCurrentTime() >= 11;
    }
}

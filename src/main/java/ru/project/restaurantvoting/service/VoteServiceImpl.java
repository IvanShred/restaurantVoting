package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantvoting.model.Vote;
import ru.project.restaurantvoting.repository.vote.VoteRepository;
import ru.project.restaurantvoting.util.DateUtil;

import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFound;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getByUserId(int userId) {
        return checkNotFound(voteRepository.getByUserId(userId, DateUtil.getCurrentDate()), "userId=" + userId);
    }


    @Override
    @Transactional
    public Vote vote(int restaurantId, int userId) {
        if (voteRepository.getByUserId(userId, DateUtil.getCurrentDate()) != null) {
            DateUtil.checkTime();
            voteRepository.delete(userId, DateUtil.getCurrentDate());
        }
        return voteRepository.save(DateUtil.getCurrentDate(), userId, restaurantId);
    }

    @Override
    @Transactional
    public void cancelVote(int userId) {
        checkNotFound(voteRepository.delete(userId, DateUtil.getCurrentDate()), "userId=" + userId);
    }
}

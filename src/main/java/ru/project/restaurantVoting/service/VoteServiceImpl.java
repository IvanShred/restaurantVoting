package ru.project.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.repository.vote.VoteRepository;
import ru.project.restaurantVoting.util.DateUtil;

import static ru.project.restaurantVoting.util.ValidationUtil.checkNotFound;

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

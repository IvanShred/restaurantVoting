package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantvoting.model.Vote;
import ru.project.restaurantvoting.repository.vote.VoteRepository;
import ru.project.restaurantvoting.to.VoteResponseTo;
import ru.project.restaurantvoting.util.DateUtil;

import java.time.LocalDate;
import java.util.Objects;

import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFound;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getByUserId(int userId) {
        return checkNotFound(voteRepository.getByUserId(userId, LocalDate.now()), "userId=" + userId);
    }


    @Override
    @Transactional
    public VoteResponseTo vote(LocalDate date, int restaurantId, int userId) {
        DateUtil.checkTime();

        Vote voteFromDb = voteRepository.getByUserId(userId, LocalDate.now());

        Integer id = null;
        if (voteFromDb != null) {
            id = voteFromDb.getId();
        }

        Vote vote = voteRepository.save(id, date, userId, restaurantId);

        return new VoteResponseTo(vote.getId(), date, userId, restaurantId);
    }

    @Override
    @Transactional
    public void cancelVote(int userId) {
        checkNotFound(voteRepository.delete(userId, LocalDate.now()), "userId=" + userId);
    }
}

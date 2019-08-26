package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantvoting.model.Vote;
import ru.project.restaurantvoting.repository.vote.VoteRepository;
import ru.project.restaurantvoting.to.VoteResponseTo;
import ru.project.restaurantvoting.util.DateUtil;

import java.time.LocalDate;

import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFound;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public VoteResponseTo getByUserId(int userId, LocalDate date) {
        Vote vote = checkNotFound(voteRepository.getByUserId(userId, date != null ? date : LocalDate.now()),
                "userId=" + userId);

        return new VoteResponseTo(vote.getId(), vote.getDateVote(), vote.getUser().getId(), vote.getRestaurant().getId());
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
}

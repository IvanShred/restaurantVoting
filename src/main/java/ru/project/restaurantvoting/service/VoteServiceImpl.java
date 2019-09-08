package ru.project.restaurantvoting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantvoting.model.Vote;
import ru.project.restaurantvoting.repository.CrudRestaurantRepository;
import ru.project.restaurantvoting.repository.CrudUserRepository;
import ru.project.restaurantvoting.repository.CrudVoteRepository;
import ru.project.restaurantvoting.to.response.VoteResponseTo;
import ru.project.restaurantvoting.util.DateUtil;
import ru.project.restaurantvoting.util.VoteUtil;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VoteServiceImpl implements VoteService {

    private final CrudVoteRepository crudVoteRepository;

    private final CrudUserRepository crudUserRepository;

    private final CrudRestaurantRepository crudRestaurantRepository;

    public VoteServiceImpl(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository,
                           CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public VoteResponseTo getByUserId(int userId, LocalDate date) {

        Vote vote = crudVoteRepository.findByUser(userId, date != null ? date : LocalDate.now())
                .orElseThrow(() -> new NotFoundException(String.format("Not found vote with userId=%d", userId)));

        return VoteUtil.convertToResponse(vote.getId(), vote.getDateVote(), vote.getUser().getId(), vote.getRestaurant().getId());
    }


    @Override
    @Transactional
    public VoteResponseTo vote(LocalTime time, int restaurantId, int userId) {
        DateUtil.checkTime(time);

        Vote voteFromDb = crudVoteRepository.findByUser(userId, LocalDate.now())
                .orElse(null);

        Integer id = null;
        if (voteFromDb != null) {
            id = voteFromDb.getId();
        }

        Vote vote = crudVoteRepository.save(
                new Vote(id, LocalDate.now(), crudUserRepository.getOne(userId), crudRestaurantRepository.getOne(restaurantId)));

        return VoteUtil.convertToResponse(vote.getId(), LocalDate.now(), userId, restaurantId);
    }
}

package ru.project.restaurantvoting.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantvoting.model.Vote;
import ru.project.restaurantvoting.repository.restaurant.CrudRestaurantRepository;
import ru.project.restaurantvoting.repository.user.CrudUserRepository;

import java.time.LocalDate;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Autowired
    private CrudVoteRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Vote save(Integer voteId, LocalDate date, int userId, int restaurantId) {
        return crudRepository.save(new Vote(voteId, date, crudUserRepository.getOne(userId), crudRestaurantRepository.getOne(restaurantId)));
    }

    @Override
    public boolean delete(int userId, LocalDate date) {
        return crudRepository.delete(userId, date) != 0;
    }

    @Override
    public Vote getByUserId(int userId, LocalDate date) {
        return crudRepository.findByUser(userId, date);
    }
}

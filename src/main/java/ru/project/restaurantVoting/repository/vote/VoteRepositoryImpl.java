package ru.project.restaurantVoting.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.repository.restaurant.CrudRestaurantRepository;
import ru.project.restaurantVoting.repository.user.CrudUserRepository;
import ru.project.restaurantVoting.util.DateUtil;

import java.time.LocalDate;
import java.util.Optional;

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
    public Vote save(LocalDate date, int userId, int restaurantId) {
//        if (!vote.isNew() && !getById(vote.getId(), userId).isPresent()) {
//            return null;
//        }
//        vote.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(new Vote(date, crudUserRepository.getOne(userId), crudRestaurantRepository.getOne(restaurantId)/*new Restaurant(restaurantId)*/));
    }

    @Override
    public boolean delete(int userId, LocalDate date) {
        return crudRepository.delete(userId, date) != 0;
    }

//    @Override
//    public Optional<Vote> getById(int id, int userId) {
//        return crudRepository.findById(id, userId);
//    }

    @Override
    public Vote getByUserId(int userId, LocalDate date) {
        return crudRepository.findByUser(userId, date);
    }
}

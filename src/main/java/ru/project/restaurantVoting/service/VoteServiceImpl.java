package ru.project.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Restaurant;
import ru.project.restaurantVoting.model.User;
import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.repository.meal.CrudMealRepository;
import ru.project.restaurantVoting.repository.meal.MealRepository;
import ru.project.restaurantVoting.repository.user.CrudUserRepository;
import ru.project.restaurantVoting.repository.user.UserRepository;
import ru.project.restaurantVoting.repository.vote.CrudVoteRepository;
import ru.project.restaurantVoting.repository.vote.VoteRepository;
import ru.project.restaurantVoting.to.MealsRestaurant;
import ru.project.restaurantVoting.util.DateUtil;
import ru.project.restaurantVoting.util.MealsUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Vote create(Vote vote, int userId) {
//        checkTime();
//        return crudRepository.save(vote);
//
//    }
//
//    @Override
//    public Vote update(Vote vote, int userId) {
//        checkTime();
//        return crudRepository.save(vote);
//    }
//
//    @Override
//    public void delete(int id, int userId) {
//        checkTime();
//        crudRepository.delete(id, userId);
//    }
//
//    @Override
//    public Optional<Vote> getById(int id, int userId) {
//        return crudRepository.findById(id, userId);
//    }
//
//    @Override
//    public Optional<Vote> getByUserId(int userId) {
//        return crudRepository.findByUser(userId, DateUtil.getCurrentDate());
//    }


    @Override
    public List<MealsRestaurant> getMenu() {
        return MealsUtil.getAllForRestaurants(mealRepository.getAll(DateUtil.getCurrentDate()));
    }

    @Override
    @Transactional
    public Vote vote(int restaurantId, int userId) {
        if (voteRepository.getByUserId(userId, DateUtil.getCurrentDate()).isPresent()) {
            DateUtil.checkTime();
            voteRepository.delete(userId, DateUtil.getCurrentDate());
        }
        return voteRepository.save(DateUtil.getCurrentDate(), userId, restaurantId);
    }

    @Override
    @Transactional
    public void cancelVote(int userId) {
        DateUtil.checkTime();
        voteRepository.delete(userId, DateUtil.getCurrentDate());
    }
}

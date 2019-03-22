package ru.project.restaurantVoting.repositoty.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.project.restaurantVoting.model.Vote;
import ru.project.restaurantVoting.repositoty.user.CrudUserRepository;
import ru.project.restaurantVoting.repositoty.vote.CrudVoteRepository;
import ru.project.restaurantVoting.repositoty.vote.VoteRepository;
import ru.project.restaurantVoting.util.DateUtil;

import java.util.Optional;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Autowired
    private CrudVoteRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && !getById(vote.getId(), userId).isPresent()) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Optional<Vote> getById(int id, int userId) {
        return crudRepository.findById(id, userId);
    }

    @Override
    public Optional<Vote> getByUserId(int userId) {
        return crudRepository.findByUser(userId, DateUtil.getCurrentDate());
    }
}

package ru.project.restaurantVoting.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.dateVote=:date")
    int delete(@Param("userId") int userId, @Param("date") LocalDate date);

    @Override
    @Transactional
    Vote save(Vote vote);

    //@Modifying
    @Query("SELECT v FROM  Vote v WHERE v.id=:id AND v.user.id=:userId")
    Optional<Vote> findById(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.dateVote=:date")
    Optional<Vote> findByUser(@Param("userId") int userId, @Param("date") LocalDate date);
}

package ru.project.restaurantVoting.repository.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantVoting.model.Restaurant;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    Restaurant getOne(Integer id);
}

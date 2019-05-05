package ru.project.restaurantVoting.repository.user;

import ru.project.restaurantVoting.model.User;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    User getByEmail(String email);

}

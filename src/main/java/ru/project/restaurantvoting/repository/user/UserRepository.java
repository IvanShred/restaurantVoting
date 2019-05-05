package ru.project.restaurantvoting.repository.user;

import ru.project.restaurantvoting.model.User;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    User getByEmail(String email);

}

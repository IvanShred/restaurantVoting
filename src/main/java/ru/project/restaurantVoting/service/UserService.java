package ru.project.restaurantVoting.service;

import ru.project.restaurantVoting.model.User;
import ru.project.restaurantVoting.util.exception.NotFoundException;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

}

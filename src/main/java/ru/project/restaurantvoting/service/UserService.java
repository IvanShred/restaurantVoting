package ru.project.restaurantvoting.service;

import ru.project.restaurantvoting.model.User;
import ru.project.restaurantvoting.util.exception.NotFoundException;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

}

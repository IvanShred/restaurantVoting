package ru.project.restaurantvoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.project.restaurantvoting.AuthorizedUser;
import ru.project.restaurantvoting.model.User;
import ru.project.restaurantvoting.repository.CrudUserRepository;
import ru.project.restaurantvoting.util.exception.NotFoundException;

import static ru.project.restaurantvoting.util.UserUtil.prepareToSave;
import static ru.project.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final CrudUserRepository crudUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(CrudUserRepository crudUserRepository, PasswordEncoder passwordEncoder) {
        this.crudUserRepository = crudUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");

        return crudUserRepository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(crudUserRepository.delete(id) != 0, id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = crudUserRepository.getByEmail(email.toLowerCase()).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}

package by.alenavp.vote.service;

import by.alenavp.vote.model.User;
import by.alenavp.vote.repository.UserRepository;
import by.alenavp.vote.to.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static by.alenavp.vote.util.UserUtil.prepareToSave;
import static by.alenavp.vote.util.UserUtil.updateUserFromTo;
import static by.alenavp.vote.util.ValidationUtil.checkExisting;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Service
public class UserService {

    private static final String NULL_USER_MSG = "User must be not null";
    private static final String NULL_EMAIL_MSG = "Email must be not null";
    private static final String NULL_PASSWORD_MSG = "Password must be not null";

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User get(int id) {
        return checkExisting(repository.get(id));
    }

    public User getByEmail(String email) {
        Assert.notNull(email, NULL_EMAIL_MSG);
        return checkExisting(repository.getByEmail(email));
    }

    public User create(User user) {
        Assert.notNull(user.getPassword(), NULL_PASSWORD_MSG);
        Assert.notNull(user, NULL_USER_MSG);
        return checkExisting(prepareAndSave(user));
    }

    @Transactional
    public void update(UserTo updated) {
        Assert.notNull(updated, NULL_USER_MSG);
        User user = get(updated.id());
        checkExisting(prepareAndUpdate(updateUserFromTo(user, updated)));
    }

    @Transactional
    public void update(User user) {
        Assert.notNull(user, NULL_USER_MSG);
        checkExisting(prepareAndUpdate(user));
    }

    public void delete(int id) {
        checkExisting(repository.delete(id));
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    private User prepareAndUpdate(User user) {
        if (user.getPassword() == null) {
            user.setPassword(repository.get(user.id()).getPassword());
            return repository.save(user);
        }
        return prepareAndSave(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user, passwordEncoder));
    }
}

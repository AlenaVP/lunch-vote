package by.alenavp.vote.web.user;

import by.alenavp.vote.model.User;
import by.alenavp.vote.service.UserService;
import by.alenavp.vote.to.UserTo;
import by.alenavp.vote.validate.HasId;
import by.alenavp.vote.validate.UniqueMailValidator;
import by.alenavp.vote.validate.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.List;

import static by.alenavp.vote.util.UserUtil.getUserFromTo;
import static by.alenavp.vote.util.ValidationUtil.assureIdConsistent;
import static by.alenavp.vote.util.ValidationUtil.checkNew;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public abstract class AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    @Autowired
    @Qualifier("defaultValidator")
    private Validator validator;

    @Autowired
    private UniqueMailValidator emailValidator;

    public User get(int id) {
        log.info("get user {}", id);
        return service.get(id);
    }

    public List<User> getAll() {
        log.info("get all users");
        return service.getAll();
    }

    public User getByEmail(String email) {
        log.info("get user by email {}", email);
        return service.getByEmail(email);
    }

    public User create(User user) {
        log.info("create user {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User createFromTo(UserTo user) {
        log.info("create user from to {}", user);
        return service.create(getUserFromTo(user));
    }

    public void update(UserTo user) {
        log.info("update user with id {}", user.id());
        service.update(user);
    }

    public void update(User user) throws BindException {
        log.info("update user with id {} from to", user.id());
        service.update(user);
    }

    public void delete(int id) {
        log.info("delete user with id {}", id);
        service.delete(id);
    }

    protected void validateBeforeUpdate(HasId user, int id) throws BindException {
        assureIdConsistent(user, id);
        DataBinder binder = new DataBinder(user);
        binder.addValidators(emailValidator, validator);
        binder.validate(View.Web.class);
        if (binder.getBindingResult().hasErrors()) {
            throw new BindException(binder.getBindingResult());
        }
    }
}

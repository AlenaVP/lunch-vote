package by.alenavp.vote.validate;

import by.alenavp.vote.model.User;
import by.alenavp.vote.to.UserTo;
import by.alenavp.vote.util.UserUtil;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = UserUtil.getToFromUser(user);
    }

    public int getId() {
        return userTo.id();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
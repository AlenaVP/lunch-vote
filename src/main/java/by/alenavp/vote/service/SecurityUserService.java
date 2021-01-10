package by.alenavp.vote.service;

import by.alenavp.vote.model.User;
import by.alenavp.vote.validate.AuthorizedUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Service("securityUserService")
public class SecurityUserService implements UserDetailsService {

    private final UserService service;

    public SecurityUserService(UserService service) {
        this.service = service;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = service.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}

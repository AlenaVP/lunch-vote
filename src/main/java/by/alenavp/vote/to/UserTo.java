package by.alenavp.vote.to;

import by.alenavp.vote.model.User;
import by.alenavp.vote.validate.HasEmail;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public class UserTo extends NamedTo implements HasEmail {

    @NotBlank
    @Email
    @SafeHtml(whitelistType = NONE)
    private String email;

    @Size(min = 6, max = 100)
    private String password;

    public UserTo() {
    }

    public UserTo(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

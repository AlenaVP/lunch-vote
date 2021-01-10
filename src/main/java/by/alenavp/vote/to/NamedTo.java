package by.alenavp.vote.to;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public abstract class NamedTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 125)
    @SafeHtml(whitelistType = NONE)
    protected String name;

    public NamedTo() {
    }

    public NamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

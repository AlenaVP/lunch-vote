package by.alenavp.vote.to;

import by.alenavp.vote.validate.HasId;

import java.io.Serializable;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public abstract class BaseTo implements HasId, Serializable {

    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

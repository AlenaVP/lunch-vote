package by.alenavp.vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @JsonIgnore
    private List<Lunch> menus;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getMenus());
    }

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant(Integer id, String name) {
        this(id, name, Collections.emptyList());
    }

    public Restaurant(String name, List<Lunch> menus) {
        this(null, name, menus);
    }

    public Restaurant(Integer id, String name, List<Lunch> menus) {
        super(id, name);
        this.menus = menus;
    }

    public List<Lunch> getMenus() {
        return menus;
    }

    public void setMenus(List<Lunch> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package by.alenavp.vote.model;

import by.alenavp.vote.validate.View;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Entity
@Table(name = "lunches")
public class Lunch extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "lunch_dishes",
            joinColumns = @JoinColumn(name = "lunch_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    @OrderBy("name, price ASC")
    private List<Dish> dishes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "votes",
            joinColumns = @JoinColumn(name = "lunch_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @OrderBy("name")
    @JsonIgnore
    private List<User> users;

    public Lunch() {
    }

    public Lunch(Lunch lunch) {
        this(lunch.getId(), lunch.getRestaurant(), lunch.getDate(), lunch.getDishes());
    }

    public Lunch(Restaurant restaurant, LocalDate date, List<Dish> dishes) {
        this(null, restaurant, date, dishes);
    }

    public Lunch(Restaurant restaurant, LocalDate date, List<Dish> dishes, List<User> users) {
        this(null, restaurant, date, dishes, users);
    }

    public Lunch(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dishes) {
        this(id, restaurant, date, dishes, Collections.emptyList());
    }

    public Lunch(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dishes, List<User> users) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = dishes;
        this.users = users;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Lunch{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", date=" + date +
                ", dishes=" + dishes +
                '}';
    }
}

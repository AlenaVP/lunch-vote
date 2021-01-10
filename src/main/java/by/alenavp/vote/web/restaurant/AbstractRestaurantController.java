package by.alenavp.vote.web.restaurant;

import by.alenavp.vote.model.Restaurant;
import by.alenavp.vote.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static by.alenavp.vote.util.ValidationUtil.checkNew;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantService service;

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    public List<Restaurant> getAllWithActualLunch() {
        log.info("get all restaurants with actual lunch");
        return service.getAllWithActualLunch();
    }

    public List<Restaurant> getAllWithoutActualLunch() {
        log.info("get all restaurants without actual lunch");
        return service.getAllWithoutActualLunch();
    }

    public Restaurant getWinner() {
        log.info("get restaurant-winner");
        return service.getWinner();
    }

    public Restaurant getWinnerByDate(LocalDate date) {
        log.info("get restaurant-winner by date {}", date);
        return service.getWinnerByDate(date);
    }

    public Restaurant getWinning() {
        log.info("get winning restaurant");
        return service.getWinning();
    }

    public int countVotes(int id) {
        log.info("count votes for restaurant {}", id);
        return service.countVotes(id);
    }

    public int countVotesByDate(int id, LocalDate date) {
        log.info("count votes for restaurant {} by date {}", id, date);
        return service.countVotesByDate(id, date);
    }

    public void vote(int id, int userId) {
        log.info("vote by restaurant {}", id);
        service.vote(id, userId);
    }

    public void update(Restaurant restaurant) {
        log.info("update restaurant {}", restaurant.id());
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }
}

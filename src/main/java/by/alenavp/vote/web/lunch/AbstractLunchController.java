package by.alenavp.vote.web.lunch;

import by.alenavp.vote.model.Lunch;
import by.alenavp.vote.service.LunchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public abstract class AbstractLunchController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected LunchService service;

    public Lunch create(Lunch lunch) {
        log.info("create lunch {}", lunch);
        return service.create(lunch);
    }

    public Lunch get(int id) {
        log.info("get lunch {}", id);
        return service.get(id);
    }

    public Lunch getActual(int id) {
        log.info("get actual lunch for restaurant {}", id);
        return service.getActual(id);
    }

    public Lunch getForRestaurantByDate(int id, LocalDate date) {
        log.info("get lunch for restaurant {} by date {}", id, date);
        return service.getForRestaurantByDate(id, date);
    }

    public List<Lunch> getAllActual() {
        log.info("get all actual lunches");
        return service.getAllActual();
    }

    public List<Lunch> getAllByDate(LocalDate date) {
        log.info("get all lunches by date {}", date);
        return service.getAllByDate(date);
    }

    public List<Lunch> getAllForRestaurant(int id) {
        log.info("get all lunches for restaurant {}", id);
        return service.getAllForRestaurant(id);
    }

    public List<Lunch> getAll() {
        log.info("get all lunches");
        return service.getAll();
    }

    public void update(Lunch lunch) {
        log.info("update lunch {}", lunch.id());
        service.update(lunch);
    }

    public void delete(int id) {
        log.info("delete lunch {}", id);
        service.delete(id);
    }
}

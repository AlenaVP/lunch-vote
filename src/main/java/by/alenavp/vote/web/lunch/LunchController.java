package by.alenavp.vote.web.lunch;

import by.alenavp.vote.model.Lunch;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@RestController
@RequestMapping(value = LunchController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LunchController extends AbstractLunchController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Override
    @GetMapping("/lunches/{id}")
    public Lunch get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping("/{id}/lunches/actual")
    public Lunch getActual(@PathVariable int id) {
        return super.getActual(id);
    }

    @Override
    @GetMapping("/{id}/lunches/{date}")
    public Lunch getForRestaurantByDate(@PathVariable int id, @PathVariable LocalDate date) {
        return super.getForRestaurantByDate(id, date);
    }

    @Override
    @GetMapping("/lunches/actual")
    public List<Lunch> getAllActual() {
        return super.getAllActual();
    }

    @Override
    @GetMapping("/lunches/actual/{date}")
    public List<Lunch> getAllByDate(@PathVariable LocalDate date) {
        return super.getAllByDate(date);
    }

    @Override
    @GetMapping("/{id}/lunches")
    public List<Lunch> getAllForRestaurant(@PathVariable int id) {
        return super.getAllForRestaurant(id);
    }

    @Override
    @GetMapping("/lunches")
    public List<Lunch> getAll() {
        return super.getAll();
    }
}

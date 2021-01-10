package by.alenavp.vote.service;

import by.alenavp.vote.model.Lunch;
import by.alenavp.vote.repository.LunchRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static by.alenavp.vote.util.ValidationUtil.checkExisting;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Service
public class LunchService {
    private static final String NULL_LUNCH_MSG = "Lunch must be not null";

    private final LunchRepository repository;

    public LunchService(LunchRepository repository) {
        this.repository = repository;
    }

    public Lunch create(Lunch lunch) {
        Assert.notNull(lunch, NULL_LUNCH_MSG);
        return checkExisting(repository.save(lunch));
    }

    public Lunch get(int id) {
        return checkExisting(repository.get(id));
    }

    public Lunch getActual(int id) {
        return getForRestaurantByDate(id, LocalDate.now());
    }

    public Lunch getForRestaurantByDate(int id, LocalDate date) {
        return checkExisting(repository.getForRestaurantByDate(id, date));
    }

    public List<Lunch> getAllActual() {
        return getAllByDate(LocalDate.now());
    }

    public List<Lunch> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public List<Lunch> getAll() {
        return repository.getAll();
    }

    public List<Lunch> getAllForRestaurant(int id) {
        return repository.getAllForRestaurant(id);
    }

    public void update(Lunch lunch) {
        Assert.notNull(lunch, NULL_LUNCH_MSG);
        checkExisting(repository.save(lunch));
    }

    public void delete(int id) {
        checkExisting(repository.delete(id));
    }
}

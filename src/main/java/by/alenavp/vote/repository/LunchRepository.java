package by.alenavp.vote.repository;

import by.alenavp.vote.model.Lunch;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Repository
public class LunchRepository {

    private static final Sort SORT_DATE =
            Sort.by(Sort.Direction.DESC, "date")
                    .and(Sort.by(Sort.Direction.ASC, "restaurant.name"));

    private final CrudLunchRepository repository;

    public LunchRepository(CrudLunchRepository repository) {
        this.repository = repository;
    }

    public Lunch save(Lunch lunch) {
        return repository.save(lunch);
    }

    public Lunch get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Lunch> getAll() {
        return repository.findAll(SORT_DATE);
    }

    public Lunch getForRestaurantByDate(int id, LocalDate date) {
        return repository.getForRestaurantByDate(id, date);
    }

    public List<Lunch> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    public List<Lunch> getAllForRestaurant(int id) {
        return repository.getAllForRestaurant(id);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }
}

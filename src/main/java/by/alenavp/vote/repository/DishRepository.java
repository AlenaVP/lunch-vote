package by.alenavp.vote.repository;

import by.alenavp.vote.model.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Repository
public class DishRepository {
    private static final Sort SORT_NAME_PRICE = Sort.by(Sort.Direction.ASC, "name", "price");

    private final CrudDishRepository repository;

    public DishRepository(CrudDishRepository repository) {
        this.repository = repository;
    }

    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    public Dish getReference(int id) {
        return repository.getOne(id);
    }

    public List<Dish> getAll() {
        return repository.findAll(SORT_NAME_PRICE);
    }

    public boolean isUsing(int id) {
        return repository.countUsing(id) > 0;
    }

    public boolean addToLunch(int lunchId, int id) {
        return repository.addToLunch(lunchId, id) != 0;
    }

    public boolean deleteFromLunch(int lunchId, int id) {
        return repository.deleteFromLunch(lunchId, id) != 0;
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }
}

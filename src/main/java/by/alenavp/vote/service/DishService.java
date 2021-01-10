package by.alenavp.vote.service;

import by.alenavp.vote.model.Dish;
import by.alenavp.vote.repository.DishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static by.alenavp.vote.util.ValidationUtil.checkExisting;
import static by.alenavp.vote.util.ValidationUtil.checkUsing;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Service
public class DishService {
    private static final String NULL_DISH_MSG = "Dish must be not null";

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, NULL_DISH_MSG);
        return checkExisting(repository.save(dish));
    }

    public Dish get(int id) {
        return checkExisting(repository.get(id));
    }

    public List<Dish> getAll() {
        return repository.getAll();
    }

    public void addToLunch(int lunchId, int id) {
        checkExisting(repository.addToLunch(lunchId, id));
    }

    public void deleteFromLunch(int lunchId, int id) {
        checkExisting(repository.deleteFromLunch(lunchId, id));
    }

    public void update(Dish dish) {
        Assert.notNull(dish, NULL_DISH_MSG);
        checkExisting(repository.save(dish));
    }

    @Transactional
    public void delete(int id) {
        checkUsing(repository.isUsing(id));
        checkExisting(repository.delete(id));
    }
}

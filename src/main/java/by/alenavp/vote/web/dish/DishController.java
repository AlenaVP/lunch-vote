package by.alenavp.vote.web.dish;

import by.alenavp.vote.model.Dish;
import by.alenavp.vote.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static by.alenavp.vote.util.ValidationUtil.checkNew;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/rest/admin/restaurants/lunches";
    static final String DISHES_URL = "/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());
    protected final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @PostMapping(DISHES_URL)
    public ResponseEntity<Dish> createWithLocation(@RequestBody @Valid Dish dish) {
        log.info("create dish {}", dish);
        checkNew(dish);
        Dish created = service.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + DISHES_URL + "/{id}")
                .buildAndExpand(dish.id()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(DISHES_URL + "/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish {}", id);
        return service.get(id);
    }

    @GetMapping(DISHES_URL)
    public List<Dish> getAll() {
        log.info("get all dishes");
        return service.getAll();
    }

    @PutMapping(DISHES_URL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish) {
        log.info("update dish {}", dish.id());
        service.update(dish);
    }

    @PutMapping("/{lunchId}" + DISHES_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToLunch(@PathVariable int lunchId, @PathVariable int id) {
        log.info("add dish {} to lunch {}", id, lunchId);
        service.addToLunch(lunchId, id);
    }

    @DeleteMapping("/{lunchId}" + DISHES_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromLunch(@PathVariable int lunchId, @PathVariable int id) {
        log.info("delete dish {} from lunch {}", id, lunchId);
        service.deleteFromLunch(lunchId, id);
    }

    @DeleteMapping(DISHES_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete dish {}", id);
        service.delete(id);
    }
}
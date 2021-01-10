package by.alenavp.vote.web.restaurant;

import by.alenavp.vote.model.Restaurant;
import by.alenavp.vote.validate.AuthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/profile/restaurants";

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping("/winner")
    public Restaurant getWinner() {
        return super.getWinner();
    }

    @Override
    @GetMapping("/winning")
    public Restaurant getWinning() {
        return super.getWinning();
    }

    @Override
    @GetMapping("/winner/{date}")
    public Restaurant getWinnerByDate(@PathVariable LocalDate date) {
        return super.getWinnerByDate(date);
    }

    @Override
    @GetMapping
    public List<Restaurant> getAllWithActualLunch() {
        return super.getAllWithActualLunch();
    }

    @Override
    @GetMapping("/{id}/rating")
    public int countVotes(@PathVariable int id) {
        return super.countVotes(id);
    }

    @Override
    @GetMapping("/{id}/rating/{date}")
    public int countVotesByDate(@PathVariable int id, @PathVariable LocalDate date) {
        return super.countVotesByDate(id, date);
    }

    @PostMapping("/{id}/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        super.vote(id, authUser.getId());
    }
}

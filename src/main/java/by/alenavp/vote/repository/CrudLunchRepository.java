package by.alenavp.vote.repository;

import by.alenavp.vote.model.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Transactional(readOnly = true)
public interface CrudLunchRepository extends JpaRepository<Lunch, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Lunch l WHERE l.id = :id")
    int delete(@Param("id") int id);

    @Query("SELECT l FROM Lunch l WHERE l.date = :date ORDER BY l.restaurant.name ASC")
    List<Lunch> getAllByDate(@Param("date") LocalDate date);

    @Query("SELECT l FROM Lunch l WHERE l.restaurant.id = :id ORDER BY l.date DESC")
    List<Lunch> getAllForRestaurant(@Param("id") int id);

    @Query("SELECT DISTINCT l FROM Lunch l WHERE l.date=:date AND l.restaurant.id = :id")
    Lunch getForRestaurantByDate(@Param("id") int id, @Param("date") LocalDate date);
}

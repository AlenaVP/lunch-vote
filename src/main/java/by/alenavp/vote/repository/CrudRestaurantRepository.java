package by.alenavp.vote.repository;

import by.alenavp.vote.model.Restaurant;
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
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query(value = "SELECT DISTINCT * FROM restaurants WHERE id IN (SELECT restaurant_id " +
            "FROM (SELECT restaurant_id, COUNT(*) AS rest_count FROM votes AS vote, lunches AS lunch, " +
            "restaurants AS restaurant " +
            "WHERE vote.lunch_id=lunch.id AND lunch.restaurant_id=restaurant.id AND lunch.date = :date " +
            "GROUP BY restaurant_id ORDER BY rest_count DESC LIMIT 1))", nativeQuery = true)
    Restaurant getWinnerByDate(@Param("date") LocalDate date);

    @Query(value = "SELECT COUNT(*) FROM votes AS vote WHERE EXISTS(SELECT * FROM lunches " +
            "WHERE id=vote.lunch_id AND date = :date AND restaurant_id = :id)", nativeQuery = true)
    int countVotesByDate(@Param("id") int id, @Param("date") LocalDate date);

    @Query(value = "SELECT COUNT(*) FROM votes WHERE user_id = :id AND date=now()", nativeQuery = true)
    int countUserVotesToday(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO votes (user_id, lunch_id) " +
            "VALUES (:userId, (SELECT DISTINCT lunch_id FROM lunches AS lunch " +
            "WHERE lunch.restaurant_id = :id AND lunch.date = now()))", nativeQuery = true)
    void vote(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE votes SET lunch_id = (SELECT DISTINCT lunch.id FROM lunches AS lunch " +
            "WHERE lunch.restaurant_id = :id AND lunch.date = now()) " +
            "WHERE USER_ID = :userId AND DATE = TODAY()", nativeQuery = true)
    void reVote(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT r FROM Restaurant r WHERE EXISTS (SELECT r FROM Lunch l " +
            "WHERE l.restaurant.id=r.id AND l.date = :date) ORDER BY r.name")
    List<Restaurant> getAllWithActualLunch(@Param("date") LocalDate date);

    @Query("SELECT r FROM Restaurant r WHERE NOT EXISTS (SELECT r FROM Lunch l " +
            "WHERE l.restaurant.id=r.id AND l.date = :date) ORDER BY r.name")
    List<Restaurant> getAllWithoutActualLunch(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id = :id")
    int delete(@Param("id") int id);
}

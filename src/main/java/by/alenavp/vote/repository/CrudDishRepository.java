package by.alenavp.vote.repository;

import by.alenavp.vote.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Query(value = "SELECT COUNT(*) FROM lunch_dishes WHERE dish_id=:id", nativeQuery = true)
    int countUsing(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO lunch_dishes (dish_id, lunch_id) VALUES (:id, :lunchId)", nativeQuery = true)
    int addToLunch(@Param("lunchId") int lunchId, @Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM lunch_dishes WHERE lunch_id=:lunchId AND dish_id=:id", nativeQuery = true)
    int deleteFromLunch(@Param("lunchId") int lunchId, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}

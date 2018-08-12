package org.harsh.arya.fooddelivery.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {
    public Restaurant getById(Integer restaurantId);
    @Query("SELECT DISTINCT a FROM Restaurant a "+
            "INNER JOIN a.cuisines b " +
            "WHERE b LIKE %:cuisine%  AND a.name LIKE %:name% AND a.city LIKE %:city%")
    public List<Restaurant> Search(@Param("name") String name, @Param("cuisine") String cuisine, @Param("city") String city);
}

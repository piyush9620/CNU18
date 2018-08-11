package org.harsh.arya.fooddelivery.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {
    public Restaurant getById(Integer restaurantId);
    // @Query("select distinct (id) from Restaurant inner join cuisines on cuisines.restaurant_id = restaurant.id where name like %:name% and cuisines like %:cuisine% and city like %:city%")

   // @Query("select distinct (r.id) from Cuisine inner join  on cuisines.restaurant_id = restaurant.id where name like %:name% and cuisines like %:cuisine% and city like %:city%")
   // public List<Restaurant> Search(String name, String cuisine,String city);

    public List<Restaurant> getAllByNameContainingAndCuisinesInAndCityContains(String name, List<String> cuisine,String city);
}

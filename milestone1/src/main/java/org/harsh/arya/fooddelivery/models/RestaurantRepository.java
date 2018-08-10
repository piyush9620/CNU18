package org.harsh.arya.fooddelivery.models;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {
    public Restaurant getById(Integer restaurantId);

}

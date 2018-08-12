package org.harsh.arya.fooddelivery;

import org.harsh.arya.fooddelivery.models.Restaurant;

import java.util.Arrays;


public class RestaurantFactory {

    public  Restaurant createRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Flying Colour");
        restaurant.setCity("Delhi");
        restaurant.setCuisines(Arrays.asList("Italian","Mexican"));
        restaurant.setLatitude((Float)(float)89.45);
        restaurant.setLongitude((Float)(float)89.45);
        restaurant.setRating((float)8.1);
        restaurant.setIs_open(false);
        return  restaurant;
    }

    public  Restaurant createEmptyRestaurant(){
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Flying Colour");
        return  restaurant;
    }
}

package org.harsh.arya.fooddelivery.controller;

import lombok.extern.slf4j.Slf4j;
import org.harsh.arya.fooddelivery.models.Item;
import org.harsh.arya.fooddelivery.models.ItemRepository;
import org.harsh.arya.fooddelivery.models.Restaurant;
import org.harsh.arya.fooddelivery.models.RestaurantRepository;
import org.harsh.arya.fooddelivery.response.Response;
import org.harsh.arya.fooddelivery.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value="/restaurants")
    public ResponseEntity<Response> searchRestaurant(@RequestParam(value = "name", defaultValue = "") String name,
                                                     @RequestParam(value = "cuisine", defaultValue = "") String cuisine,
                                                     @RequestParam(value = "city", defaultValue = "") String city){
        List<Restaurant> restaurants = restaurantRepository.getAllByNameContainingAndCuisinesInAndCityContains(name, Arrays.asList(cuisine),city);
        return new ResponseEntity<Response>( new SuccessResponse(restaurants),HttpStatus.OK);
    }

    @GetMapping(value="/items")
    public ResponseEntity<Response> searchItem(@RequestParam(value = "name", defaultValue = "") String name,
                                               @RequestParam(value = "maxPrice", defaultValue = Float.MAX_VALUE+"") Float maxPrice,
                                               @RequestParam(value = "minPrice", defaultValue = "0.0") Float minPrice){
        log.debug(name+" "+maxPrice+" "+minPrice);
        List<Item> items =  itemRepository.getAllByNameContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(name,minPrice,maxPrice);
        return new ResponseEntity<Response>( new SuccessResponse(items),HttpStatus.OK);
    }

}

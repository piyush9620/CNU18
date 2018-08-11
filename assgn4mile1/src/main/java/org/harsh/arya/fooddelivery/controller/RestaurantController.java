package org.harsh.arya.fooddelivery.controller;


import org.harsh.arya.fooddelivery.models.Item;
import org.harsh.arya.fooddelivery.models.Restaurant;
import org.harsh.arya.fooddelivery.models.RestaurantRepository;
import org.harsh.arya.fooddelivery.utils.ErrorResponse;
import org.harsh.arya.fooddelivery.utils.PostResponse;
import org.harsh.arya.fooddelivery.utils.Response;
import org.harsh.arya.fooddelivery.utils.SuccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController()
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private Boolean checkId(Integer id){

        Restaurant restaurant = restaurantRepository.getById(id) ;
        return restaurant !=null && !restaurant.getIsDeleted();
    }

    private boolean checkNull(Restaurant restaurant){
        if(restaurant == null || restaurant.getIsDeleted()){
            return false;
        }
        return true;
    }

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<Response> findById(@PathVariable @NotNull Integer restaurantId) {
       Restaurant restaurant =  restaurantRepository.getById(restaurantId);

       if(checkNull(restaurant)){
           SuccessResponse response = new SuccessResponse();
           response.setData(restaurant);
           logger.debug("succesfully fetched restaurant");
           return new ResponseEntity<Response>(response,HttpStatus.OK);
       }else{
           logger.info("restaurant not found");
           ErrorResponse response = new ErrorResponse();
           response.setReason("ID not found");
           return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
       }

    }

    @PostMapping(value="")
    public ResponseEntity<Response> addRestaurant(@RequestBody Restaurant restaurant){
        if(!checkNull(restaurant) || !Validators.validateRestaurant(restaurant)){
            logger.info("restaurant not found");
            ErrorResponse response = new ErrorResponse();
            response.setReason("ID not found");
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }
        else{
            logger.info("restaurant created");
            restaurantRepository.save(restaurant);
            SuccessResponse response = new SuccessResponse();
            response.setData(restaurant);
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);
        }


    }

    @PutMapping(value="/{restaurantId}")
    public ResponseEntity<Response> updateRestaurant(@RequestBody Restaurant restaurant,@PathVariable @NotNull Integer restaurantId){
        if(!checkNull(restaurant)){
           logger.info("restaurant not updated");
            ErrorResponse response = new ErrorResponse();
            response.setReason("Restaurant  not validated");
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }
        if(Validators.validateRestaurant(restaurant)){

                logger.info("restaurant update");
                restaurant.setId(restaurantId);
                restaurantRepository.save(restaurant);
                SuccessResponse response = new SuccessResponse();
                response.setData(restaurant);
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            }
        else{
            logger.info("restaurant id not found");
            ErrorResponse response = new ErrorResponse();
            response.setReason("Restaurant id not found");
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping(value = "/{restaurantId}")
    public ResponseEntity<Response> deleteRestaurant(@PathVariable @NotNull Integer restaurantId){
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        if(checkNull(restaurant)){
            logger.info("deleted");
            restaurant.setIsDeleted(true);
            SuccessResponse response = new SuccessResponse();
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            logger.info("restaurant id not found");
            ErrorResponse response = new ErrorResponse();
            response.setReason("Restaurant id not found");
            return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
        }

    }

}

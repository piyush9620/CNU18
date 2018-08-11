package org.harsh.arya.fooddelivery.controller;

import lombok.extern.slf4j.Slf4j;
import org.harsh.arya.fooddelivery.models.Item;
import org.harsh.arya.fooddelivery.models.ItemRepository;
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
@RequestMapping("/api/restaurants/")
@Slf4j
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private Boolean checkId(Integer id){
        Item item = itemRepository.getById(id);
        return checkNull(item);
    }

    private boolean checkNull(Item item){
        if(item == null || item.getIsDeleted()){
            return true;
        }
        return false;
    }

    @GetMapping(value = "/{restaurantId}/items/{itemId}")
    public ResponseEntity<Response> getItem(@PathVariable @NotNull Integer restaurantId, @PathVariable @NotNull Integer itemId) {
        Item item = itemRepository.getById(itemId);

        if(!checkNull(item)){
            SuccessResponse response = new SuccessResponse();
            response.setData(item);
            return new ResponseEntity<Response>(response,HttpStatus.OK);
        }else{
            ErrorResponse response = new ErrorResponse();
            response.setReason("ID not found");
            return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/{restaurantId}/items")
    public ResponseEntity<Response> createItem(@PathVariable @NotNull Integer restaurantId,@RequestBody Item item){
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        if(restaurant !=null && !restaurant.getIsDeleted() && Validators.validateItem(item)){
            item.setRestaurant(restaurant);
            itemRepository.save(item);
            SuccessResponse response = new SuccessResponse();
            response.setData(item);
            return new ResponseEntity<Response>(response, HttpStatus.CREATED);

        }else{
            ErrorResponse response = new ErrorResponse();
            response.setReason("Restaurant id not found");
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value="/{restaurantId}/items/{itemId}")
    public ResponseEntity<Response> updateItem(@PathVariable @NotNull Integer restaurantId,@PathVariable @NotNull Integer itemId,@RequestBody Item item){
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        log.debug(item.toString());
        if(checkNull(item)){
            ErrorResponse response = new ErrorResponse();
            response.setReason("item id not found");
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }
        if(restaurant !=null){
            Item itemDb = itemRepository.getById(itemId);

            if (itemDb==null || itemDb.getRestaurant().getId() != restaurantId || !Validators.validateItem(item)){
                ErrorResponse response = new ErrorResponse();
                response.setReason("restaurant key not has found");
                return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
            }
            log.debug("restaurant id verified");
            item.setId(itemId);
            item.setRestaurant(restaurant);
            itemRepository.save(item);
            SuccessResponse response = new SuccessResponse();
            response.setData(item);
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            ErrorResponse response = new ErrorResponse();
            response.setReason("restaurant id not found");
            return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{restaurantId}/items/{itemId}")
    public ResponseEntity<Response> deleteItem(@PathVariable @NotNull Integer restaurantId,@PathVariable @NotNull Integer itemId){
        Item item = itemRepository.getById(itemId);
        if(!checkNull(item)){
            item.setIsDeleted(true);
            itemRepository.save(item);
            SuccessResponse response = new SuccessResponse();
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        }else{
            ErrorResponse response = new ErrorResponse();
            response.setReason("Item id not found");
            return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
        }
    }

}

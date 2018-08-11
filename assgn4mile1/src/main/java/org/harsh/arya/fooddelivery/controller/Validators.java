package org.harsh.arya.fooddelivery.controller;

import lombok.extern.slf4j.Slf4j;
import org.harsh.arya.fooddelivery.models.Item;
import org.harsh.arya.fooddelivery.models.Restaurant;

@Slf4j
public class Validators {

    public static boolean validateRestaurant(Restaurant restaurant){
        if(checkNullrestaurant(restaurant)){
            return false;
        }
        Float rating = restaurant.getRating();
        Float latitude = restaurant.getLatitude();
        Float longitude = restaurant.getLongitude();

        return checkRange(rating,0,100) &&
                checkRange(latitude,-90,90) &&
                checkRange(longitude,-180,180);

    }

    private static boolean checkRange(Float number,int rangea,int rangeb){
        if( number == null || (number>rangeb || number<rangea))
        {
            log.info("valiadation failed for "+number+ " "+rangea+" "+rangeb);
            return false;
        }

        return true;
    }

    private static boolean checkNullrestaurant(Restaurant restaurant){
       return restaurant == null || restaurant.getCity() == null ||restaurant.getCuisines() == null || restaurant.getLatitude() ==null
               || restaurant.getLongitude() ==null || restaurant.getRating() ==null ;
    }

    private static boolean checkNullItem(Item item){
        return item == null || item.getName() == null || item.getPrice() == null;
    }





    public static boolean validateItem(Item item){
        Float price = item.getPrice();
        if(checkNullItem(item)){
            return false;
        }
        return checkRange(price,0,Integer.MAX_VALUE);

    }

}

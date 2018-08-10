package org.harsh.arya.fooddelivery.controller;

import lombok.extern.slf4j.Slf4j;
import org.harsh.arya.fooddelivery.models.Restaurant;

@Slf4j
public class Validators {

    public static boolean validateRestaurant(Restaurant restaurant){
            Float rating = restaurant.getRating();
            Float latitude = restaurant.getLatitude();
            Float longitude = restaurant.getLongitude();
            if(checkNullrestaurant(restaurant)){
                return false;
            }
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
        if(restaurant == null){
            return true;
        }
        if(restaurant.getCity() ==null){
            return true;

        }
        if(restaurant.getCuisines() ==null){
            return true;
        }

            return false;
    }


}

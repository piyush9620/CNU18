package org.harsh.arya.fooddelivery.controller;

import lombok.extern.slf4j.Slf4j;
import org.harsh.arya.fooddelivery.models.Restaurant;

@Slf4j
public class Validators {

    public static boolean validateRestaurant(Restaurant restaurant){
            Float rating = restaurant.getRating();
            Float latitude = restaurant.getLatitude();
            Float longitude = restaurant.getLongitude();
            return checkRange(rating,0,100) &&
                    checkRange(latitude,-90,90) &&
                    checkRange(longitude,-180,180);

    }

    private static boolean checkRange(Float number,int rangea,int rangeb){
        if( (number>rangeb || number<rangea))
        {
            log.info("valiadation failed for "+number+ " "+rangea+" "+rangeb);
            return false;
        }

        return true;
    }


}

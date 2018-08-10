package org.harsh.arya.fooddelivery.controller;

import org.harsh.arya.fooddelivery.models.Restaurant;

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
        if(number != null && (number>rangeb || number<rangea))
            return false;
        return true;
    }


}

package org.harsh.arya.fooddelivery.utils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Validators {


    public static boolean checkRange(Float number,int rangea,int rangeb){
        if( number == null || (number>rangeb || number<rangea))
        {
            log.info("valiadation failed for "+number+ " "+rangea+" "+rangeb);
            return false;
        }

        return true;
    }



}

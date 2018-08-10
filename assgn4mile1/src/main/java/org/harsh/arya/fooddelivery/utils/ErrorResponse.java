package org.harsh.arya.fooddelivery.utils;

import lombok.Data;

@Data
public class ErrorResponse extends  Response{
    private String reason;
    private String status ="failure";
}

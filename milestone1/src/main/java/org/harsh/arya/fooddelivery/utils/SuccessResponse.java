package org.harsh.arya.fooddelivery.utils;

import lombok.Data;

@Data
public class SuccessResponse extends Response {
    private String status ="success";
    private Object data;
}

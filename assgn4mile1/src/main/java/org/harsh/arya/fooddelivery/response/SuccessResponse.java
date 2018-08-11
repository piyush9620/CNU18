package org.harsh.arya.fooddelivery.response;

import lombok.Data;
import org.harsh.arya.fooddelivery.utils.Response;

@Data
public class SuccessResponse extends Response {
    private String status ="success";
    private Object data;
}

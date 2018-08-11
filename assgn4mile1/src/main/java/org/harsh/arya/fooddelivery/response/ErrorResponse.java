package org.harsh.arya.fooddelivery.response;

import lombok.Data;
import org.harsh.arya.fooddelivery.utils.Response;

@Data
public class ErrorResponse extends Response {
    private String reason;
    private String status ="failure";
}

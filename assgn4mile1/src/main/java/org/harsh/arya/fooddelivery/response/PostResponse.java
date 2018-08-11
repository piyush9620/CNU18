package org.harsh.arya.fooddelivery.response;

import lombok.Data;
import org.harsh.arya.fooddelivery.utils.Response;

@Data
public class PostResponse extends Response {
    private String status ="success";
    private Integer id;
}

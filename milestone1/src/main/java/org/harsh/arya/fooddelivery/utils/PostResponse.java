package org.harsh.arya.fooddelivery.utils;

import lombok.Data;

@Data
public class PostResponse extends Response {
    private String status ="success";
    private Integer id;
}

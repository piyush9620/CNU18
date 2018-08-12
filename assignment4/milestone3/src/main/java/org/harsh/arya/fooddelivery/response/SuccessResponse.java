package org.harsh.arya.fooddelivery.response;

import lombok.Data;

@Data
public class SuccessResponse extends Response {
    private String status ="success";
    private Object data;
    public SuccessResponse(Object data){
        this.data = data;
    }
    public SuccessResponse(){

    }
}

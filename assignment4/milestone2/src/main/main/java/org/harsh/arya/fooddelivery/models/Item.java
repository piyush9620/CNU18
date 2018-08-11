package org.harsh.arya.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static org.harsh.arya.fooddelivery.utils.Validators.checkRange;

@Getter
@Setter
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "restaurant_item")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private Float price;
    @ManyToOne
    private Restaurant restaurant;
    @JsonIgnore
    private Boolean isDeleted = false;

    public String toString(){
        return "Item("+id+","+name+","+price+","+isDeleted+")";
    }
    private  boolean checkNullItem(){
        return  this.getName() == null || this.getPrice() == null;
    }

    public  boolean validate(){
        if(checkNullItem()){
            return false;
        }
        return checkRange(price,0,Integer.MAX_VALUE);

    }

}

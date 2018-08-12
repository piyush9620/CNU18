package org.harsh.arya.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static org.harsh.arya.fooddelivery.utils.Validators.checkRange;

@Where(clause = "is_deleted = 0")
@Data
@Entity // This tells Hibernate to make a table out of this class
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String city;
    private Float latitude;
    private  Float longitude;
    private Float rating;
    private Boolean is_open;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Item> items;

    @ElementCollection
    @CollectionTable(name="cuisines")
    private List<String> cuisines;

    @JsonIgnore
    private Boolean isDeleted = false;

    public  boolean validate(){
        if(checkNullrestaurant()){
            return false;
        }
        return checkRange(rating,0,100) &&
                checkRange(latitude,-90,90) &&
                checkRange(longitude,-180,180);

    }

    private  boolean checkNullrestaurant(){
        return  this.getCity() == null ||this.getCuisines() == null || this.getLatitude() ==null
                || this.getLongitude() ==null || this.getRating() ==null ;
    }

}

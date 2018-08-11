package org.harsh.arya.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

}

package org.harsh.arya.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

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

}

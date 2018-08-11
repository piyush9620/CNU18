package org.harsh.arya.fooddelivery.models;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Integer> {
    public  Item getById(Integer itemId);
    //@Query("select item from restaurant_item item where item.name like ?1 and item.price < ?2 and item.price > ?3")
    public List<Item> getAllByNameContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(String name,Float priceGt,Float priceLt);
}

package org.harsh.arya.fooddelivery.models;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Integer> {
    public  Item getById(Integer itemId);
    public List<Item> getAllByNameContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(String name,Float priceGt,Float priceLt);

}

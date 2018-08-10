package org.harsh.arya.fooddelivery.models;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Integer> {
    public  Item getById(Integer itemId);
    //public Item getByIdAndgetByIsDeletedFalse(Integer itemId);

}

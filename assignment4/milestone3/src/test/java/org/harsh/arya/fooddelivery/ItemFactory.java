package org.harsh.arya.fooddelivery;

import org.harsh.arya.fooddelivery.models.Item;

public class ItemFactory {

    public Item createItem(){
        Item item = new Item();
        item.setName("Fapilo");
        item.setPrice((float)98.78);
        return item;
    }

    public Item createEmptyItem(){
        Item item = new Item();
        item.setName("Fapilo");
        return item;
    }

}

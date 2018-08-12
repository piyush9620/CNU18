package org.harsh.arya.fooddelivery;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.harsh.arya.fooddelivery.models.Item;
import org.harsh.arya.fooddelivery.models.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@AutoConfigureMockMvc
public class ItemApiTest {
    @Autowired
    MockMvc mockMvc;
    ItemFactory itemFactory = new ItemFactory();
    RestaurantFactory restaurantFactory = new RestaurantFactory();
    ObjectMapper objectMapper = new ObjectMapper();

    private int returnIdAfterCreated() throws Exception{
        Restaurant restaurant = restaurantFactory.createRestaurant();
        MvcResult result = mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(restaurant)))
                .andExpect(status().isCreated())
                .andReturn();
        MockHttpServletResponse mockResponse=result.getResponse();
        JsonNode node = objectMapper.readTree(mockResponse.getContentAsString());

        return node.get("data").get("id").intValue();
    }


    private int getCreatedItemId(Item item,int resId) throws Exception{

        MvcResult result = mockMvc.perform(post("/api/restaurants/"+resId+"/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isCreated())
                .andReturn();
        MockHttpServletResponse mockResponse=result.getResponse();
        JsonNode node = objectMapper.readTree(mockResponse.getContentAsString());
        return node.get("data").get("id").intValue();
    }

    public void getItemAfterCreated(Item item,Integer itemId,Integer restaurantId) throws Exception{
        mockMvc.perform(
                get("/api/restaurants/" + restaurantId + "/items/" + itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.name").value(item.getName()))
                .andExpect(jsonPath("$.data.price").value(item.getPrice()));
    }


    @Test
    public void getCreatedItemSuccessFully() throws Exception{
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createItem();
        int itemID = getCreatedItemId(item,resId);
        getItemAfterCreated(item,itemID,resId);
    }

    @Test
    public void postInvaliditemCreated() throws Exception{
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createEmptyItem();
        mockMvc.perform(post("/api/restaurants/"+resId+"/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void testDeletedItem() throws Exception {
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createItem();
        int itemId = getCreatedItemId(item,resId);
        mockMvc.perform(delete("/api/restaurants/"+resId+"/items/"+itemId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/restaurants/"+resId+"/items/"+itemId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testDeletedNonExistentItem() throws Exception{
        Integer resId = returnIdAfterCreated();
        mockMvc.perform(delete("/api/restaurants/"+resId+"/items/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetNonExistentItem() throws Exception{
        Integer resId = returnIdAfterCreated();
        mockMvc.perform(get("/api/restaurants/"+resId+"/items/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPutNonExistentItem() throws Exception{
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createItem();
        mockMvc.perform(put("/api/restaurants/"+resId+"/items/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutItem() throws Exception{
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createItem();
        int itemId = getCreatedItemId(item,resId);
        item.setPrice((float)50.0);
        mockMvc.perform(put("/api/restaurants/"+resId+"/items/"+itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isOk());
        getItemAfterCreated(item,itemId,resId);
    }

    @Test
    public void testPutInvalidItem() throws Exception{
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createItem();
        int itemId = getCreatedItemId(item,resId);
        item.setPrice((float)-5.0);
        mockMvc.perform(put("/api/restaurants/"+resId+"/items/"+itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testPutInvalidRestaurant() throws Exception{
        Integer resId = returnIdAfterCreated();
        Item item = itemFactory.createItem();
        int itemId = getCreatedItemId(item,resId);
        mockMvc.perform(put("/api/restaurants/0/items/"+itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchItemSuccessfully() throws Exception {
        Integer resId = returnIdAfterCreated();
        getCreatedItemId(itemFactory.createItem(),resId);
        Item item = itemFactory.createItem();
        item.setName("not used before");
        getCreatedItemId(item,resId);
        mockMvc.perform(get("/api/items")
                .param("name", item.getName())
                .param("minPrice", (item.getPrice()-5)+"")
                .param("maxPrice", (item.getPrice()+5)+"")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").value(Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value(item.getName()));
    }



}

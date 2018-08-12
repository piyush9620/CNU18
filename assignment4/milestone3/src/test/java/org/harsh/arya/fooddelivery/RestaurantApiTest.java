package org.harsh.arya.fooddelivery;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.harsh.arya.fooddelivery.models.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Java6Assertions.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@AutoConfigureMockMvc
public class RestaurantApiTest {
    @Autowired
    MockMvc mockMvc;
    RestaurantFactory restaurantFactory = new RestaurantFactory();
    ObjectMapper objectMapper = new ObjectMapper();


    private  void testGetForCreatedSuccess(int id,Restaurant restaurant) throws Exception{
        mockMvc.perform(get("/api/restaurants/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.name").value(restaurant.getName()))
                .andExpect(jsonPath("$.data.rating").value(restaurant.getRating()))
                .andExpect(jsonPath("$.data.latitude").value(restaurant.getLatitude()))
                .andExpect(jsonPath("$.data.longitude").value(restaurant.getLongitude()))
                .andExpect(jsonPath("$.data.cuisines").value(Matchers.hasSize(restaurant.getCuisines().size())))
                .andExpect(jsonPath("$.data.is_open").value(restaurant.getIs_open()))
                .andExpect(jsonPath("$.data.city").value(restaurant.getCity()));

    }



    private int returnIdAfterCreated(Restaurant restaurant) throws Exception{
        MvcResult result = mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(restaurant)))
                .andExpect(status().isCreated())
                .andReturn();
        MockHttpServletResponse mockResponse=result.getResponse();
        JsonNode node = objectMapper.readTree(mockResponse.getContentAsString());

        return node.get("data").get("id").intValue();
    }

    @Test
    public void testCreateRestaurantSuccessfully() throws Exception {
        Restaurant restaurant = restaurantFactory.createRestaurant();
        int id = returnIdAfterCreated(restaurant);
        testGetForCreatedSuccess(id,restaurant);
    }

    @Test
    public void testCreateRestaurantFailed() throws Exception {
        Restaurant restaurant = restaurantFactory.createEmptyRestaurant();
        mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(restaurant)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testDeletedRestaurant() throws Exception {
        Restaurant restaurant = restaurantFactory.createRestaurant();
        int id = returnIdAfterCreated(restaurant);
        mockMvc.perform(delete("/api/restaurants/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/restaurants/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testDeletedNonExistentRestaurant() throws Exception{
        mockMvc.perform(delete("/api/restaurants/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetNonExistentRestaurant() throws Exception{
        mockMvc.perform(get("/api/restaurants/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPutNonExistentRestaurant() throws Exception{
        Restaurant restaurant =  restaurantFactory.createRestaurant();
        mockMvc.perform(put("/api/restaurants/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(restaurant)))
                        .andExpect(status().isBadRequest());
    }

    @Test
    public void testPutRestaurant() throws Exception{
        Restaurant restaurant =  restaurantFactory.createRestaurant();
        int id = returnIdAfterCreated(restaurant);
        restaurant.setRating((float)5.0);
        mockMvc.perform(put("/api/restaurants/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(restaurant)))
                .andExpect(status().isOk());
        testGetForCreatedSuccess(id,restaurant);
    }

    @Test
    public void testPutInvalidRestaurant() throws Exception{
        Restaurant restaurant =  restaurantFactory.createRestaurant();
        int id = returnIdAfterCreated(restaurant);
        restaurant.setRating((float)-5.0);
        mockMvc.perform(put("/api/restaurants/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(restaurant)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testSearchRestaurantSuccessfully() throws Exception {
        returnIdAfterCreated(restaurantFactory.createRestaurant());
        Restaurant restaurant = restaurantFactory.createRestaurant();
        restaurant.setName("not used before");
        returnIdAfterCreated(restaurant);
        mockMvc.perform(get("/api/restaurants")
                .param("name", restaurant.getName())
                .param("city", restaurant.getCity())
                .param("cuisine", restaurant.getCuisines().get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(restaurant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").value(Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value(restaurant.getName()));

    }

}

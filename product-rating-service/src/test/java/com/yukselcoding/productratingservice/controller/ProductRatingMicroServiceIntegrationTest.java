package com.yukselcoding.productratingservice.controller;


import com.yukselcoding.productratingservice.model.AverageRating;
import com.yukselcoding.productratingservice.model.ProductRatings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ProductRatingMicroServiceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Returns rated product list")
    public void returns_getRatedProductsByCustomer_success() throws Exception {
        ProductRatings response = restTemplate.getForObject(createUriWithPort("rated-products/2"), ProductRatings.class);
        assertTrue(response.getProductRatings().size() > 0);
    }

    @Test
    @DisplayName("Returns rated product empty list")
    public void returns_getRatedProductsByCustomer_empty_list() throws Exception {
        ProductRatings response = restTemplate.getForObject(createUriWithPort("rated-products/999"), ProductRatings.class);
        assertEquals(0, response.getProductRatings().size());
    }

    @Test
    @DisplayName("Returns average rating of products from customers")
    public void returns_getAverageRatingOfProduct_success() throws Exception {
        AverageRating response = restTemplate.getForObject(createUriWithPort("average/1"), AverageRating.class);
        assertEquals(3.85, response.getAverage());
    }

    @Test
    @DisplayName("Returns average rating of products from customers as zero")
    public void returns_getAverageRatingOfProduct_zero() throws Exception {
        AverageRating response = restTemplate.getForObject(createUriWithPort("average/999"), AverageRating.class);
        assertEquals(0, response.getAverage());
    }


    private String createUriWithPort(String s) {
        return String.format("http://localhost:%s/rating/%s", port, s);
    }
}

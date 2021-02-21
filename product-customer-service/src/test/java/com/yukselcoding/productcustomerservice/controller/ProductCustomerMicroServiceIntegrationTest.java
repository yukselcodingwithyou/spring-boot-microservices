package com.yukselcoding.productcustomerservice.controller;


import com.yukselcoding.productcustomerservice.model.AverageSpends;
import com.yukselcoding.productcustomerservice.model.BoughtProducts;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCustomerMicroServiceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @DisplayName("Returns bought products by customer")
    public void test_getBoughtProductByCustomer_success() {
        BoughtProducts response = restTemplate.getForObject(createUriWithPort("bought-products/2"), BoughtProducts.class);
        assertTrue(response.getBoughtProducts().size() > 0);
    }

    @Test
    @DisplayName("Returns bought products by customer as empty list")
    public void test_getBoughtProductByCustomer_empty_list() {
        BoughtProducts response = restTemplate.getForObject(createUriWithPort("bought-products/999"), BoughtProducts.class);
        assertEquals(0, response.getBoughtProducts().size());
    }

    @Test
    @DisplayName("Returns average spent money by customer as zero")
    public void test_getAverageSpendOfCustomer_returns_zero() {
        AverageSpends response = restTemplate.getForObject(createUriWithPort("average-spend/999"), AverageSpends.class);
        assertEquals(0, response.getAverage());
    }

    @Test
    @DisplayName("Returns average spent money by customer as average")
    public void test_getAverageSpendOfCustomer_returns_average() {
        AverageSpends response = restTemplate.getForObject(createUriWithPort("average-spend/2"), AverageSpends.class);
        assertEquals(42.85, response.getAverage());
    }

    @Test
    @DisplayName("Returns average spent money by customer per product as zero")
    public void test_getAverageSpendOfCustomerPerProduct_returns_zero() {
        AverageSpends response = restTemplate.getForObject(createUriWithPort("average-spend/999/999"), AverageSpends.class);
        assertEquals(0, response.getAverage());
    }


    @Test
    @DisplayName("Returns average spent money by customer per product as average")
    public void test_getAverageSpendOfCustomerPerProduct_returns_average() {
        AverageSpends response = restTemplate.getForObject(createUriWithPort("average-spend/2/1"), AverageSpends.class);
        assertEquals(45.6, response.getAverage());
    }


    private String createUriWithPort(String s) {
        return String.format("http://localhost:%s/customer/%s", port, s);
    }
}

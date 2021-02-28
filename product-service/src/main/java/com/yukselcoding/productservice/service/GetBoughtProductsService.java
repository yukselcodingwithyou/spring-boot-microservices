package com.yukselcoding.productservice.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yukselcoding.productservice.model.BoughtProduct;
import com.yukselcoding.productservice.model.BoughtProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class GetBoughtProductsService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getBoughtProductsFallbackMethod",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public BoughtProducts getBoughtProducts(String customerID) {
        return restTemplate.getForObject("http://product-customer-service/customer/bought-products/" + customerID, BoughtProducts.class);
    }

    public BoughtProducts getBoughtProductsFallbackMethod(String customerID) {
        return new BoughtProducts(Collections.singletonList(new BoughtProduct("No product", 0d)));
    }
}

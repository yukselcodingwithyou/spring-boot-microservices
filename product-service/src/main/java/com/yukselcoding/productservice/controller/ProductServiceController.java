package com.yukselcoding.productservice.controller;


import com.yukselcoding.productservice.model.AverageRating;
import com.yukselcoding.productservice.model.BoughtProductRatingInfo;
import com.yukselcoding.productservice.model.BoughtProductRatings;
import com.yukselcoding.productservice.model.BoughtProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductServiceController {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/customer-rated-bought-average/{customerID}")
    public BoughtProductRatings getBoughtProductRatingsByCustomer(@PathVariable("customerID") String customerID) {
        BoughtProducts boughtProducts = restTemplate.getForObject("http://product-customer-service/customer/bought-products/" + customerID, BoughtProducts.class);
        List<BoughtProductRatingInfo> results = boughtProducts.getBoughtProducts().stream().map(boughtProduct -> {
            AverageRating averageRating = restTemplate.getForObject("http://product-rating-service/rating/average/" + boughtProduct.getProductID(), AverageRating.class);
            return new BoughtProductRatingInfo(averageRating.getAverage(), boughtProduct.getProductID(), boughtProduct.getPrice());
        }).collect(Collectors.toList());
        return new BoughtProductRatings(results);
    }




}

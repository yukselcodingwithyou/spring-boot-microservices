package com.yukselcoding.productratingservice.controller;


import com.yukselcoding.productratingservice.model.AverageRating;
import com.yukselcoding.productratingservice.model.ProductRating;
import com.yukselcoding.productratingservice.model.ProductRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating")
public class ProductRatingController {


    private static List<ProductRating> productRatingList = Arrays.asList(
            new ProductRating("1", 4.5, "2"),
            new ProductRating("2", 3.3, "2"),
            new ProductRating("3", 3.4, "2"),
            new ProductRating("4", 3.5, "1"),
            new ProductRating("5", 2.6, "1"),
            new ProductRating("1", 3.2, "1"),
            new ProductRating("5", 4.6, "2")
    );

    @GetMapping("/rated-products/{customerID}")
    public ProductRatings getRatedProductsByCustomer(@PathVariable("customerID") String customerID) {
        List<ProductRating> productRatingsOfCustomer = productRatingList.stream()
                .filter(productRating -> productRating.getCustomerID().equals(customerID))
                .collect(Collectors.toList());
        return new ProductRatings(productRatingsOfCustomer);
    }

    @GetMapping("/average/{productID}")
    public AverageRating getAverageRatingOfProduct(@PathVariable("productID") String productID) {
        double average = productRatingList
                .stream()
                .filter(productRating -> productRating.getProductID().equals(productID))
                .mapToDouble(ProductRating::getRating)
                .average().orElse(0d);
        return new AverageRating(average);
    }
}

package com.yukselcoding.productservice.controller;


import com.yukselcoding.productservice.model.BoughtProductRatingInfo;
import com.yukselcoding.productservice.model.BoughtProductRatings;
import com.yukselcoding.productservice.model.BoughtProducts;
import com.yukselcoding.productservice.service.GetBoughtProductRatingInfoService;
import com.yukselcoding.productservice.service.GetBoughtProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductServiceController {

    @Autowired
    private GetBoughtProductsService getBoughtProductsService;

    @Autowired
    private GetBoughtProductRatingInfoService getBoughtProductRatingInfoService;


    @GetMapping("/customer-rated-bought-average/{customerID}")
    public BoughtProductRatings getBoughtProductRatingsByCustomer(@PathVariable("customerID") String customerID) {
        BoughtProducts boughtProducts = getBoughtProductsService.getBoughtProducts(customerID);
        List<BoughtProductRatingInfo> results = boughtProducts.getBoughtProducts()
                .stream()
                .map(boughtProduct -> getBoughtProductRatingInfoService.getBoughtProductRatingInfo(boughtProduct))
                .collect(Collectors.toList());
        return new BoughtProductRatings(results);
    }
}

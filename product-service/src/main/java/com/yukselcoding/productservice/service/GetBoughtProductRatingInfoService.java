package com.yukselcoding.productservice.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yukselcoding.productservice.model.AverageRating;
import com.yukselcoding.productservice.model.BoughtProduct;
import com.yukselcoding.productservice.model.BoughtProductRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetBoughtProductRatingInfoService {


    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getBoughtProductRatingInfoFallbackMethod")
    public BoughtProductRatingInfo getBoughtProductRatingInfo(BoughtProduct boughtProduct) {
        AverageRating averageRating = restTemplate.getForObject("http://product-rating-service/rating/average/" + boughtProduct.getProductID(), AverageRating.class);
        return new BoughtProductRatingInfo(averageRating.getAverage(), boughtProduct.getProductID(), boughtProduct.getPrice());
    }

    public BoughtProductRatingInfo getBoughtProductRatingInfoFallbackMethod(BoughtProduct boughtProduct) {
        return new BoughtProductRatingInfo(0d, "No product", 0d);
    }
}

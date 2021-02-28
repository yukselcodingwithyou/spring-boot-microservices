package com.yukselcoding.productservice.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @HystrixCommand(fallbackMethod = "getBoughtProductRatingInfoFallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public BoughtProductRatingInfo getBoughtProductRatingInfo(BoughtProduct boughtProduct) {
        AverageRating averageRating = restTemplate.getForObject("http://product-rating-service/rating/average/" + boughtProduct.getProductID(), AverageRating.class);
        return new BoughtProductRatingInfo(averageRating.getAverage(), boughtProduct.getProductID(), boughtProduct.getPrice());
    }

    public BoughtProductRatingInfo getBoughtProductRatingInfoFallbackMethod(BoughtProduct boughtProduct) {
        return new BoughtProductRatingInfo(0d, "No product", 0d);
    }
}

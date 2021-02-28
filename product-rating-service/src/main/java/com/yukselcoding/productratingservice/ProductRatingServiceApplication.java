package com.yukselcoding.productratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ProductRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductRatingServiceApplication.class, args);
    }

}

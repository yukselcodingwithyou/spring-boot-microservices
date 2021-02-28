package com.yukselcoding.productcustomerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCustomerServiceApplication.class, args);
    }

}

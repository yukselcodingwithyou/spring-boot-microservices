package com.yukselcoding.productcustomerservice.controller;


import com.yukselcoding.productcustomerservice.model.AverageSpends;
import com.yukselcoding.productcustomerservice.model.BoughtProduct;
import com.yukselcoding.productcustomerservice.model.BoughtProducts;
import com.yukselcoding.productcustomerservice.model.ProductCustomer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class ProductCustomerController {

    private static List<ProductCustomer> productCustomerList = Arrays.asList(
        new ProductCustomer("1", "2", 45.6),
        new ProductCustomer("2", "3", 35.6),
        new ProductCustomer("3", "2", 23.6),
        new ProductCustomer("4", "2", 56.6),
        new ProductCustomer("1", "1", 45.6),
        new ProductCustomer("1", "4", 45.6),
        new ProductCustomer("1", "2", 45.6)
    );

    @GetMapping("/bought-products/{customerID}")
    public BoughtProducts getBoughtProductByCustomer(@PathVariable("customerID") String customerID) {
        List<BoughtProduct> boughtProducts = productCustomerList.stream()
                .filter(pc -> pc.getCustomerID().equals(customerID))
                .map(productCustomer -> new BoughtProduct(productCustomer.getProductID(), productCustomer.getPrice()))
                .collect(Collectors.toList());
        return new BoughtProducts(boughtProducts);
    }

    @GetMapping("/average-spend/{customerID}")
    public AverageSpends getAverageSpendOfCustomer(@PathVariable("customerID") String customerID) {
        double average = productCustomerList.stream()
                .filter(pc -> pc.getCustomerID().equals(customerID))
                .mapToDouble(ProductCustomer::getPrice).average().orElse(0d);
        return new AverageSpends(Double.valueOf(String.format("%.2f", average)));
    }

    @GetMapping("/average-spend/{customerID}/{productID}")
    public AverageSpends getAverageSpendOfCustomerPerProduct(@PathVariable("customerID") String customerID,
                                                   @PathVariable("productID") String productID) {
        double average = productCustomerList.stream()
                .filter(pc -> pc.getCustomerID().equals(customerID) && pc.getProductID().equals(productID))
                .mapToDouble(ProductCustomer::getPrice).average().orElse(0d);
        return new AverageSpends(Double.valueOf(String.format("%.2f", average)));
    }
}

package com.yukselcoding.productcustomerservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCustomer {
    private String productID;
    private String customerID;
    private Double price;
}

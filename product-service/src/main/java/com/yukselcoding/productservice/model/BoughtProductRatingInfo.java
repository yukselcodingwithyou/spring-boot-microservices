package com.yukselcoding.productservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoughtProductRatingInfo {
    private Double rating;
    private String product;
    private Double price;
}

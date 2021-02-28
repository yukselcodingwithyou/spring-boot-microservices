package com.yukselcoding.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BoughtProductRatings {
    private List<BoughtProductRatingInfo> info;
}

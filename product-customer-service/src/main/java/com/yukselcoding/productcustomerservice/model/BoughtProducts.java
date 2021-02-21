package com.yukselcoding.productcustomerservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoughtProducts {
    private List<BoughtProduct> boughtProducts;
}

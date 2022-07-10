package com.bysesoft.bysesoft.ports.imputs.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesDto {

    private Double total;
    private SellerDto seller;
    private Double commission;
    private List<ProductDto> product;

    public SalesDto(Double total, Double commission, List<ProductDto> product) {
        this.total = total;
        this.commission = commission;
        this.product = product;
    }
}

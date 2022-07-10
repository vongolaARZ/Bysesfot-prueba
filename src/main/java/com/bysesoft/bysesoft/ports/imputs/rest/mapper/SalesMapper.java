package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Sales;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SalesDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SellerDto;

import java.util.List;
import java.util.stream.Collectors;

public class SalesMapper {

    public static SalesDto saleToDto(Sales sales){
        SalesDto salesDto = new SalesDto();
        salesDto.setCommission(sales.getCommission());
        salesDto.setTotal(sales.getTotal());
        salesDto.setSeller(new SellerDto(sales.getSeller().getName(),sales.getSeller().getSalary()));
        List<ProductDto>productsDto = sales.getProductList()
                .stream()
                .map(product -> {
                    return new ProductDto(product.getName(),product.getPrice(),product.getCategory().getName());
                }).collect(Collectors.toList());
        salesDto.setProduct(productsDto);

        return salesDto;
    }
}

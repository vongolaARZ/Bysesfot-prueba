package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto toDto(Product product){

        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());

        return productDto;
    }

    public static List<ProductDto> productToDtoList(List<Product> products){
        List<ProductDto> productDtoList = products
                .stream().map(product -> {
                    return new ProductDto(product.getName(),product.getPrice(),product.getCategory().getName());
                }).collect(Collectors.toList());
        return productDtoList;
    }
}

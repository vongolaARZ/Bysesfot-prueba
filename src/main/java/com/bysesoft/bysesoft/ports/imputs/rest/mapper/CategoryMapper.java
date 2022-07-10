package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.CategoryDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDto toDto(Category category){

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName(category.getName());

        List<ProductDto> productDtoList = category.getProducts()
                .stream().map(product -> {
                    return new ProductDto(product.getName(), product.getPrice(),product.getCategory().getName());
                }).collect(Collectors.toList());

        categoryDto.setProduct(productDtoList);


        return categoryDto;
    }
}

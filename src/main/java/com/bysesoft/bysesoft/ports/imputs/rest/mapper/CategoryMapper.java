package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.CategoryDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category){

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName(category.getName());

        List<ProductDto> productDtoList = category.getProducts()
                        .stream().map(product -> {
                            return new ProductDto(product.getName(),product.getPrice());
                }).collect(Collectors.toList());

        categoryDto.setProduct(productDtoList);


        return categoryDto;
    }

    public Category toEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    public List<CategoryDto> toDtoList(List<Category> categories){
        return categories
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

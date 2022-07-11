package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.CategoryDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductMapper {

    public ProductDto toDto(Product product){

        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(new CategoryDto(product.getCategory().getName()));

        return productDto;
    }

    public List<ProductDto> ToDtoList(List<Product> products){

        return products
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(new Category(productDto.getCategory().getName()));
        product.setPrice(productDto.getPrice());
        return product;
    }
}

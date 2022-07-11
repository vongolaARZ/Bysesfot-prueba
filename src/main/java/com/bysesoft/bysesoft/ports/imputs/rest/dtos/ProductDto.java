package com.bysesoft.bysesoft.ports.imputs.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;
    @NotNull
    private Double price;

    @NotBlank
    @JsonIgnoreProperties(value = "product")
    private CategoryDto  category;

    public ProductDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}

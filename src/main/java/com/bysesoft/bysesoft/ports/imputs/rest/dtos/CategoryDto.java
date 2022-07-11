package com.bysesoft.bysesoft.ports.imputs.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotBlank
    private String name;
    @JsonIgnoreProperties(value = "category")
    private List<ProductDto> product;

    public CategoryDto(String name) {
        this.name = name;
    }
}

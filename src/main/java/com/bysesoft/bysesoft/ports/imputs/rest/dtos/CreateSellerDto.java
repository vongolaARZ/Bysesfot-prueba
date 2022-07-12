package com.bysesoft.bysesoft.ports.imputs.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSellerDto {

    @NotBlank
    private String name;
    @NotNull
    private Double salary;
}

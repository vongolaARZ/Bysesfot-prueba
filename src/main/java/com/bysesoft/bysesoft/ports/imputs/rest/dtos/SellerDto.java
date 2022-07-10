package com.bysesoft.bysesoft.ports.imputs.rest.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    private String name;
    private Double salary;
    private List<SalesDto> sales;

    public SellerDto(String name, Double salary){
        this.name= name;
        this.salary=salary;
    }
}

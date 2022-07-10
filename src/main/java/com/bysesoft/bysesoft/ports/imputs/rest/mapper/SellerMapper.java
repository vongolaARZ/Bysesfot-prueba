package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Seller;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SalesDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SellerDto;

import java.util.List;
import java.util.stream.Collectors;

public class SellerMapper {

    public static SellerDto toDto (Seller seller){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setName(seller.getName());
        sellerDto.setName(seller.getName());

        List<SalesDto> salesDtoList = seller.getSales()
                .stream().map(sales -> {
                    return new SalesDto(sales.getTotal(),sales.getCommission(),ProductMapper.productToDtoList(sales.getProductList()));
                }).collect(Collectors.toList());

        sellerDto.setSales(salesDtoList);

        return sellerDto;
    }
}

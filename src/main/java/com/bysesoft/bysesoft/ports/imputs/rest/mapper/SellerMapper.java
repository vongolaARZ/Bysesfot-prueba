package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.model.Sales;
import com.bysesoft.bysesoft.domain.model.Seller;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.CategoryDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SalesDto;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SellerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SellerMapper {

    public SellerDto toDto (Seller seller){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setName(seller.getName());
        sellerDto.setSalary(seller.getSalary());
        List<Sales> salesList = seller.getSales();

        List<SalesDto> salesDtoList = salesList
                .stream().map(sales -> {
                    return new SalesDto(sales.getTotal(),sales.getCommission(),toProductDtoList(sales.getProductList()));
                }).collect(Collectors.toList());

        sellerDto.setSales(salesDtoList);

        return sellerDto;
    }

    private List<ProductDto> toProductDtoList (List<Product> products){
        List<ProductDto> productDtoList = products
                .stream()
                .map(product -> {
                    return new ProductDto(product.getName(), product.getPrice(), new CategoryDto(product.getCategory().getName()));
                }).toList();

        return productDtoList;
    }

    public List<SellerDto> toDtoList(List<Seller> sellers){
        List<SellerDto> sellerDtos = sellers
                .stream().map(this::toDto).toList();

        return sellerDtos;
    }
}

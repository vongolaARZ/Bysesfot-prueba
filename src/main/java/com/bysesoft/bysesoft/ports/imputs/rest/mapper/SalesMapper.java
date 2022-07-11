package com.bysesoft.bysesoft.ports.imputs.rest.mapper;

import com.bysesoft.bysesoft.domain.model.Category;
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
public class SalesMapper {

    public SalesDto saleToDto(Sales sales) {
        SalesDto salesDto = new SalesDto();
        salesDto.setCommission(sales.getCommission());
        salesDto.setTotal(sales.getTotal());
        salesDto.setSeller(new SellerDto(sales.getSeller().getName(), sales.getSeller().getSalary()));
        List<ProductDto> productsDto = sales.getProductList()
                .stream()
                .map(product -> {
                    return new ProductDto(product.getName(), product.getPrice(), new CategoryDto(product.getCategory().getName()));
                }).collect(Collectors.toList());
        salesDto.setProduct(productsDto);

        return salesDto;
    }

    public List<SalesDto> salesToDtoList(List<Sales> sales){
        return sales.stream()
                .map(this::saleToDto)
                .collect(Collectors.toList());
    }

    public Sales dtoToSale(SalesDto salesDto) {
        Sales sales = new Sales();
        sales.setSeller(new Seller(salesDto.getSeller().getName(), salesDto.getSeller().getSalary()));
        sales.setTotal(salesDto.getTotal());
        sales.setCommission(sales.getCommission());
        sales.setProductList(productsToDtosList(salesDto.getProduct()));

        return sales;

    }

    private List<Product> productsToDtosList(List<ProductDto> productDtoList){
        List<Product> productList = productDtoList
                .stream()
                .map(productDto -> {return new Product(productDto.getName(),productDto.getPrice(),new Category(productDto.getCategory().getName()));
                }).collect(Collectors.toList());

        return productList;
    }

}

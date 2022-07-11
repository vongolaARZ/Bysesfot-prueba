package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.service.ProductService;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.ProductDto;
import com.bysesoft.bysesoft.ports.imputs.rest.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable @NotNull String name){
        Product productEntity = productService.getByName(name);
        ProductDto productDto = productMapper.toDto(productEntity);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable @NotNull String category){
        List<Product> productEntityList = productService.findByCatergory(category);
        List<ProductDto> productDtoList = productMapper.ToDtoList(productEntityList);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Product>productEntityList = new ArrayList<>(productService.findAll());
        List<ProductDto> productDtoList = productMapper.ToDtoList(productEntityList);
        return new ResponseEntity<>(productDtoList,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ProductDto productDto){

        productService.create(productMapper.toEntity(productDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable @NotNull Long id){
        productService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.service.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        Product productEntity = productService.getByName(name);
        return new ResponseEntity<>(productEntity,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category){
        Product productEntity = productService.getByCatergory(category);
        return new ResponseEntity<>(productEntity, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Product>productList = new ArrayList<>(productService.findAll());
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product){
        productService.create(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

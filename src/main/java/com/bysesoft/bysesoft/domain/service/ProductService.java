package com.bysesoft.bysesoft.domain.service;

import com.bysesoft.bysesoft.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product getByName(String name);
    void create(Product product);
    List<Product> findByCatergory(String category);
    void deleteById(Long id);
    List<Product> findAll();
}

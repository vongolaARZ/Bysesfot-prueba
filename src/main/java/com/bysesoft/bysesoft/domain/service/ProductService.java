package com.bysesoft.bysesoft.domain.service;

import com.bysesoft.bysesoft.domain.model.Product;

import java.util.List;

public interface ProductService {

    Product getByName(String name);
    void create(Product product);
    Product getByCatergory(String category);
    void deleteById(Long id);
    List<Product> findAll();
}

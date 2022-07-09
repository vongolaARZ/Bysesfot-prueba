package com.bysesoft.bysesoft.domain.repository;

import com.bysesoft.bysesoft.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRespository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);
    Optional<Product> findByCategory(String category);
}

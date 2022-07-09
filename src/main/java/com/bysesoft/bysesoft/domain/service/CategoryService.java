package com.bysesoft.bysesoft.domain.service;

import com.bysesoft.bysesoft.domain.model.Category;

import java.util.List;

public interface CategoryService {

    void create(Category category);
    Category findByName(String name);
    List<Category> findAll();
    void delete(Long id);
}

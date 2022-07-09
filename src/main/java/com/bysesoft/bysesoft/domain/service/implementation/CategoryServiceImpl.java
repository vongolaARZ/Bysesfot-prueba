package com.bysesoft.bysesoft.domain.service.implementation;

import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.domain.repository.CategoryRepository;
import com.bysesoft.bysesoft.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void create(Category category){
        categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {
       return categoryRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("no existe la categoria"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new RuntimeException("No hay categorias");
        }else {
            return categories;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no existe la categoria"));
        categoryRepository.deleteById(category.getCategoryId());
    }
}

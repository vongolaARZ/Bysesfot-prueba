package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category){
       categoryService.create(category);
       return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        Category category = categoryService.findByName(name);
        return new ResponseEntity<>( category,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

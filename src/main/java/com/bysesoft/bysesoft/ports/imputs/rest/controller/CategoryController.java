package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.domain.service.CategoryService;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.CategoryDto;
import com.bysesoft.bysesoft.ports.imputs.rest.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDto dto){
        Category category = categoryMapper.toEntity(dto);
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> findByName(@PathVariable @NotNull String name){
        Category category = categoryService.findByName(name);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>( categoryDto,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<Category> categories = new ArrayList<>(categoryService.findAll());
        List<CategoryDto> categoryDtos = categoryMapper.toDtoList(categories);
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

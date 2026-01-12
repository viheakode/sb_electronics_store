package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.CategoryRequest;
import com.viheakode.electronic_store.dto.response.CategoryDto;
import com.viheakode.electronic_store.service.imp.CategoryServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody CategoryRequest categoryRequest){
        CategoryDto categoryDto = categoryServiceImp.createCategory(categoryRequest);
        return ApiResponseStructure.singleResponse("Created", categoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        List<CategoryDto> categoryDtos = categoryServiceImp.getAllCategories();
        return ApiResponseStructure.singleResponse("Ok", categoryDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        CategoryDto categoryDto = categoryServiceImp.getCategory(id);
        return ApiResponseStructure.singleResponse("Ok", categoryDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        CategoryDto categoryDto = categoryServiceImp.update(id, categoryRequest);
        return ApiResponseStructure.singleResponse("Updated", categoryDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        CategoryDto categoryDto = categoryServiceImp.delete(id);
        return ApiResponseStructure.singleResponse("Deleted", categoryDto, HttpStatus.OK);
    }
}

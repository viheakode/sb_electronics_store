package com.viheakode.electronic_store.service;

import com.viheakode.electronic_store.dto.request.CategoryRequest;
import com.viheakode.electronic_store.dto.response.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequest categoryRequest);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategory(Long id);
    CategoryDto update(Long id, CategoryRequest categoryRequest);
    CategoryDto delete(Long id);
}

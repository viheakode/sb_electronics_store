package com.viheakode.electronic_store.service.imp;

import com.viheakode.electronic_store.dto.request.CategoryRequest;
import com.viheakode.electronic_store.dto.response.CategoryDto;
import com.viheakode.electronic_store.mapper.CategoryMapper;
import com.viheakode.electronic_store.model.Category;
import com.viheakode.electronic_store.repository.CategoryRepo;
import com.viheakode.electronic_store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setUuid(UUID.randomUUID().toString());
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        categoryRepo.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(CategoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        category.setModifiedDate(new Date());
        categoryRepo.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto delete(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepo.deleteById(id);
        return CategoryMapper.toDto(category);
    }
}

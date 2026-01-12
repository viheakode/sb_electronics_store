package com.viheakode.electronic_store.service;

import com.viheakode.electronic_store.dto.request.ProductRequest;
import com.viheakode.electronic_store.dto.response.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(ProductRequest productRequest);
    ProductDto create2(ProductRequest productRequest);
    List<ProductDto> getAllProducts();
    ProductDto getProduct(Long id);
    List<ProductDto> getAllProductsByCategory(Long categoryId);
    List<ProductDto> getAllProductsByBrand(Long brandId);
    List<ProductDto> getAllProductsByCategoryAndBrand(Long categoryId, Long brandId);
    ProductDto update(Long id, ProductRequest productRequest);
    ProductDto update2(Long id, ProductRequest productRequest);
    ProductDto delete(Long id);
}

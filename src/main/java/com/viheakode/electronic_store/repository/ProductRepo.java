package com.viheakode.electronic_store.repository;

import com.viheakode.electronic_store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategory_CategoryId(Long categoryId);
    List<Product> findByBrand_BrandId(Long brandId);
    List<Product> findByCategory_CategoryIdAndBrand_BrandId(Long categoryId, Long brandId);
}

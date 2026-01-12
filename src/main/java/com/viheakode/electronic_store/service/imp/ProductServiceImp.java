package com.viheakode.electronic_store.service.imp;

import com.viheakode.electronic_store.dto.request.ProductRequest;
import com.viheakode.electronic_store.dto.response.ProductDto;
import com.viheakode.electronic_store.mapper.ProductMapper;
import com.viheakode.electronic_store.model.Brand;
import com.viheakode.electronic_store.model.Category;
import com.viheakode.electronic_store.model.Product;
import com.viheakode.electronic_store.repository.BrandRepo;
import com.viheakode.electronic_store.repository.CategoryRepo;
import com.viheakode.electronic_store.repository.ProductRepo;
import com.viheakode.electronic_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public ProductDto create(ProductRequest productRequest) {

        try {
            Category category = categoryRepo.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Brand brand = brandRepo.findById(productRequest.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));

            String imageName = fileStorageService.save(productRequest.getImage());

            Product product = new Product();
            product.setUuid(UUID.randomUUID().toString());
            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImage(imageName);
            product.setCategory(category);
            product.setBrand(brand);
            productRepo.save(product);

            return ProductMapper.toDto(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ProductDto create2(ProductRequest productRequest) {

        try {
            Category category = categoryRepo.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Brand brand = brandRepo.findById(productRequest.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));

            Map<String, String> image = cloudinaryService.upload(productRequest.getImage());
            System.out.println(image);

            Product product = new Product();
            product.setUuid(UUID.randomUUID().toString());
            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImage(image.get("url"));
            product.setBrand(brand);
            product.setCategory(category);
            productRepo.save(product);
            return ProductMapper.toDto(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductDto update2(Long id, ProductRequest productRequest) {

        try {
            Category category = categoryRepo.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Brand brand = brandRepo.findById(productRequest.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));

            Map<String, String> image = cloudinaryService.upload(productRequest.getImage());
            System.out.println(image);

            Product product = productRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImage(image.get("url"));
            product.setBrand(brand);
            product.setCategory(category);
            product.setModifiedDate(new Date());
            productRepo.save(product);
            return ProductMapper.toDto(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        List<Product> products = productRepo.findByCategory_CategoryId(categoryId);
        return products.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public List<ProductDto> getAllProductsByBrand(Long brandId) {
        List<Product> products = productRepo.findByBrand_BrandId(brandId);
        return products.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public List<ProductDto> getAllProductsByCategoryAndBrand(Long categoryId, Long brandId) {
        List<Product> products = productRepo.findByCategory_CategoryIdAndBrand_BrandId(categoryId, brandId);
        return products.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public ProductDto update(Long id, ProductRequest productRequest) {

        try {
            Category category = categoryRepo.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            Brand brand = brandRepo.findById(productRequest.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));

            Product product = productRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            String imageName = fileStorageService.save(productRequest.getImage());

            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setImage(imageName);
            product.setCategory(category);
            product.setBrand(brand);
            product.setModifiedDate(new Date());
            productRepo.save(product);
            return ProductMapper.toDto(product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDto delete(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepo.deleteById(id);
        return ProductMapper.toDto(product);
    }




}

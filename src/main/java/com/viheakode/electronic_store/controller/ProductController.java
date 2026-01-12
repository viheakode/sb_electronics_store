package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.ProductRequest;
import com.viheakode.electronic_store.dto.response.ProductDto;
import com.viheakode.electronic_store.service.imp.ProductServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @PostMapping(value = "/v1/product/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> save(@ModelAttribute ProductRequest productRequest){
        ProductDto productDto = productServiceImp.create(productRequest);
        return ApiResponseStructure.singleResponse("Created", productDto, HttpStatus.CREATED);
    }

    @GetMapping("/v1/product/")
    public ResponseEntity<Object> getAll(){
        List<ProductDto> productDtos = productServiceImp.getAllProducts();
        return ApiResponseStructure.singleResponse("Ok", productDtos, HttpStatus.OK);
    }

    @GetMapping("/v1/product/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        ProductDto productDto = productServiceImp.getProduct(id);
        return ApiResponseStructure.singleResponse("Ok", productDto, HttpStatus.OK);
    }

    @GetMapping("/v1/product/category")
    public ResponseEntity<Object> getByCategory(@RequestParam Long categoryId){
        List<ProductDto> productDtos = productServiceImp.getAllProductsByCategory(categoryId);
        return ApiResponseStructure.singleResponse("Ok", productDtos, HttpStatus.OK);
    }

    @GetMapping("/v1/product/brand")
    public ResponseEntity<Object> getByBrand(@RequestParam Long brandId){
        List<ProductDto> productDtos = productServiceImp.getAllProductsByBrand(brandId);
        return ApiResponseStructure.singleResponse("Ok", productDtos, HttpStatus.OK);
    }
    @GetMapping("/v1/product/category/brand")
    public ResponseEntity<Object> getByCategoryAndBrand(@RequestParam Long categoryId, @RequestParam Long brandId){
        List<ProductDto> productDtos = productServiceImp.getAllProductsByCategoryAndBrand(categoryId, brandId);
        return ApiResponseStructure.singleResponse("Ok", productDtos, HttpStatus.OK);
    }

    @PutMapping(value = "/v1/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> edit(@PathVariable Long id, @ModelAttribute ProductRequest productRequest){
        ProductDto productDto = productServiceImp.update(id, productRequest);
        return ApiResponseStructure.singleResponse("Updated", productDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/v1/product/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        ProductDto productDto = productServiceImp.delete(id);
        return ApiResponseStructure.singleResponse("Deleted", productDto, HttpStatus.OK);
    }

    @PostMapping(value = "/v2/product/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> save2(@ModelAttribute ProductRequest productRequest) throws Exception {
        ProductDto productDto = productServiceImp.create2(productRequest);
        return ApiResponseStructure.singleResponse("Created", productDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/v2/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> edit2(@PathVariable Long id, @ModelAttribute ProductRequest productRequest) throws Exception {
        ProductDto productDto = productServiceImp.update2(id, productRequest);
        return ApiResponseStructure.singleResponse("Updated", productDto, HttpStatus.CREATED);
    }
}

package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.BrandRequest;
import com.viheakode.electronic_store.dto.response.BrandDto;
import com.viheakode.electronic_store.service.imp.BrandServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    @Autowired
    private BrandServiceImp brandServiceImp;

    @PostMapping("/")
    public ResponseEntity<Object> createBrand(@RequestBody BrandRequest brandRequest){
        BrandDto brandDto = brandServiceImp.createBrand(brandRequest);
        return ApiResponseStructure.singleResponse("Created", brandDto, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        List<BrandDto> brandDtos = brandServiceImp.getAllBrands();
        return ApiResponseStructure.singleResponse("Ok", brandDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        BrandDto brandDto = brandServiceImp.getBrandById(id);
        return ApiResponseStructure.singleResponse("Ok", brandDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody BrandRequest brandRequest){
        BrandDto brandDto = brandServiceImp.update(id, brandRequest);
        return ApiResponseStructure.singleResponse("Updated", brandDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        BrandDto brandDto = brandServiceImp.delete(id);
        return ApiResponseStructure.singleResponse("Deleted", brandDto, HttpStatus.OK);
    }
}

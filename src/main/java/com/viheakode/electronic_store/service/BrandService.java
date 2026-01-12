package com.viheakode.electronic_store.service;

import com.viheakode.electronic_store.dto.request.BrandRequest;
import com.viheakode.electronic_store.dto.response.BrandDto;

import java.util.List;

public interface BrandService {
    BrandDto createBrand(BrandRequest brandRequest);
    List<BrandDto> getAllBrands();
    BrandDto getBrandById(Long id);
    BrandDto update(Long id, BrandRequest brandRequest);
    BrandDto delete(Long id);
}

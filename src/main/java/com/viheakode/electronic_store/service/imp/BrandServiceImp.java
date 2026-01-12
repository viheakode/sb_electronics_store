package com.viheakode.electronic_store.service.imp;

import com.viheakode.electronic_store.dto.request.BrandRequest;
import com.viheakode.electronic_store.dto.response.BrandDto;
import com.viheakode.electronic_store.mapper.BrandMapper;
import com.viheakode.electronic_store.model.Brand;
import com.viheakode.electronic_store.repository.BrandRepo;
import com.viheakode.electronic_store.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepo brandRepo;

    @Override
    public BrandDto createBrand(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setUuid(UUID.randomUUID().toString());
        brand.setBrandName(brandRequest.getBrandName());
        brand.setDescription(brandRequest.getDescription());
        brandRepo.save(brand);
        return BrandMapper.toDto(brand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        List<Brand> brands = brandRepo.findAll();
        return brands.stream().map(BrandMapper::toDto).toList();
    }

    @Override
    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        return BrandMapper.toDto(brand);
    }

    @Override
    public BrandDto update(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setBrandName(brandRequest.getBrandName());
        brand.setDescription(brandRequest.getDescription());
        brand.setModifiedDate(new Date());
        brandRepo.save(brand);
        return BrandMapper.toDto(brand);
    }

    @Override
    public BrandDto delete(Long id) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brandRepo.deleteById(id);
        return BrandMapper.toDto(brand);
    }
}

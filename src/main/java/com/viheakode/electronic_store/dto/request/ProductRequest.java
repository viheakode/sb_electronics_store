package com.viheakode.electronic_store.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private String productName;
    private String description;
    private Double price;
    private MultipartFile image;
    private Long categoryId;
    private Long brandId;
}

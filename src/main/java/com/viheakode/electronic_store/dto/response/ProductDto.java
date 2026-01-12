package com.viheakode.electronic_store.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long productId;
    private String uuid;
    private String productName;
    private String description;
    private Double price;
    private String image;
    private CategoryDto categoryDto;
    private BrandDto brandDto;
    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}

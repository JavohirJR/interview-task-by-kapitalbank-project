package com.javohir.lastproject.payload;

import lombok.Data;

@Data
public class ProductDTO {
    private String name, description;
    private double price;
    private Integer photoId;
    private Integer categoryId;
}

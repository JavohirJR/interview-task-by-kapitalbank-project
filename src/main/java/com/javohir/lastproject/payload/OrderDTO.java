package com.javohir.lastproject.payload;

import lombok.Data;

@Data
public class OrderDTO {
    private Integer quantity, customerId, productId;
}

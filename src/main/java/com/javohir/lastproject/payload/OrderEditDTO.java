package com.javohir.lastproject.payload;

import lombok.Data;

@Data
public class OrderEditDTO {
    private Integer quantity, customerId, productId, invoiceId, detailId;
}

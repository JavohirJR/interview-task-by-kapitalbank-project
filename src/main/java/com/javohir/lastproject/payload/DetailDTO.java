package com.javohir.lastproject.payload;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class DetailDTO {
    @NotNull
    private Integer quantity, orderId, productId;
}

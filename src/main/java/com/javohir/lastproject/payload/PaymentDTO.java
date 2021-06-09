package com.javohir.lastproject.payload;

import lombok.Data;

@Data
public class PaymentDTO {
    private Integer invoiceId;
    private double amount;
}

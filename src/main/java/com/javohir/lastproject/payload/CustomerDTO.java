package com.javohir.lastproject.payload;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotNull
    private String name, country, phoneNumber, text;

}

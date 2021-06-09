package com.javohir.lastproject.payload;

import com.javohir.lastproject.entity.Detail;
import com.javohir.lastproject.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOutDTO {
    private Order order;
    private List<Detail> detailList;
}

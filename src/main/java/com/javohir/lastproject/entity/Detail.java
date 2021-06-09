package com.javohir.lastproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    private Product product;

    public Detail(Integer quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }
}
